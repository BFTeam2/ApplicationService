package com.example.transactionmanagementdemo.dao;


import com.example.transactionmanagementdemo.domain.entity.Applicationworkflow;
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
}

