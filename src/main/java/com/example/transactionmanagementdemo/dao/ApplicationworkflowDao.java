package com.example.transactionmanagementdemo.dao;


import com.amazonaws.util.StringUtils;
import com.example.transactionmanagementdemo.domain.entity.Applicationworkflow;
import com.example.transactionmanagementdemo.domain.entity.OrderProduct;
import com.example.transactionmanagementdemo.domain.entity.Orders;
import com.example.transactionmanagementdemo.domain.entity.Product;
import com.example.transactionmanagementdemo.domain.request.PurchaseRequest;
import com.example.transactionmanagementdemo.exception.NotEnoughInventoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public class ApplicationworkflowDao {
    @Autowired
    SessionFactory sessionFactory;


    public List getApplicationworkflowByEmployeeIDs(Set<String> employeeIDs){
        Session session;
        List<Applicationworkflow> list = null;
        try{
            session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("SELECT id,employeeID,createDate,lastModificationDate,status,comment FROM Applicationworkflow WHERE employeeID  in(:employeeIDs) order by lastModificationDate desc ");
            q.setParameter("employeeIDs", employeeIDs);
            list = q.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List getApplicationworkflowByEmployeeID(String employeeID){
        Session session;
        List<Applicationworkflow> list = null;
        try{
            session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("SELECT id,employeeID,createDate,lastModificationDate,status,comment FROM Applicationworkflow WHERE employeeID =:employeeID");
            q.setParameter("employeeID", employeeID);
            list = q.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List getApplicationworkflowList(String type) {
        Session session;
        List<Applicationworkflow> list = null;
        try{
            session = sessionFactory.getCurrentSession();
            if(StringUtils.isNullOrEmpty(type)){
                Query q = session.createQuery("SELECT id,employeeID,createDate,lastModificationDate,status,comment FROM Applicationworkflow order by createDate desc");
                list = q.list();
            }else{
                Query q = session.createQuery("SELECT id,employeeID,createDate,lastModificationDate,status,comment FROM Applicationworkflow WHERE status =:status  order by createDate desc");
                q.setParameter("status", type);
                list = q.list();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public void reviewApplication(String status, String comment, Integer id) {
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("Update Applicationworkflow set status = :status, comment = :comment, lastModificationDate = :lastModificationDate WHERE id =:id");
            q.setParameter("status", status);
            q.setParameter("comment", comment);
            q.setParameter("lastModificationDate", new Timestamp(new Date().getTime()));
            q.setParameter("id", id);
            q.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void addApplication(Applicationworkflow applicationworkflow) {
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("INSERT INTO Applicationworkflow(employeeID, createDate, lastModificationDate)" +
                    "values('"+applicationworkflow.getEmployeeID()+"','"+applicationworkflow.getCreateDate()+"','"+applicationworkflow.getLastModificationDate()+"')");
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

