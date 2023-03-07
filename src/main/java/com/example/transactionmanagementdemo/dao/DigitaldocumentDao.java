package com.example.transactionmanagementdemo.dao;


import com.example.transactionmanagementdemo.domain.entity.Digitaldocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class DigitaldocumentDao {
    @Autowired
    SessionFactory sessionFactory;


    public List getAllDigitaldocument(){
        Session session;
        List<Digitaldocument> list = null;
        try{
            session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("SELECT id,title,type,isRequired,path,description FROM Digitaldocument");
            list = q.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

