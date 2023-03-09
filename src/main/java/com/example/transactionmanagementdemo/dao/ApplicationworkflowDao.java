package com.example.transactionmanagementdemo.dao;


import com.amazonaws.util.StringUtils;
import com.example.transactionmanagementdemo.domain.entity.Applicationworkflow;
import com.example.transactionmanagementdemo.domain.entity.Digitaldocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public class ApplicationworkflowDao {
    @Autowired
    SessionFactory sessionFactory;


//    public List getApplicationworkflowByEmployeeIDs(Set<String> employeeIDs){
//        Session session;
//        List<Applicationworkflow> list = null;
//        try{
//            session = sessionFactory.getCurrentSession();
//            Query q = session.createQuery("SELECT id,employeeID,createDate,lastModificationDate,status,comment FROM Applicationworkflow WHERE employeeID  in(:employeeIDs) order by lastModificationDate desc ");
//            q.setParameter("employeeIDs", employeeIDs);
//            list = q.list();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return list;
//    }

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

    public Applicationworkflow getApplicationWorkflowById(int applicationId){
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Applicationworkflow";
        Query<Applicationworkflow> query = session.createQuery(hql);
        return query.getResultList().get(0);
    }

    public List<Applicationworkflow> getApplicationStatus(String employeeID) {
        Session session;
        List<Applicationworkflow> list = null;
        try{
            session = sessionFactory.getCurrentSession();
            Query<Applicationworkflow> q = session.createQuery("SELECT status FROM Applicationworkflow WHERE employeeID =:employeeID");
            q.setParameter("employeeID", employeeID);
            list = q.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Applicationworkflow> getApplicationworkflowList(String status) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Applicationworkflow";
        Query<Applicationworkflow> query = session.createQuery(hql);
        return query.getResultList();
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
            Query query = session.createSQLQuery("INSERT INTO Applicationworkflow(employeeID, createDate, lastModificationDate, status)" +
                    "values('"+applicationworkflow.getEmployeeID()+"','"+applicationworkflow.getCreateDate()+"','"+applicationworkflow.getLastModificationDate()+"','"+applicationworkflow.getStatus()+"')");
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

