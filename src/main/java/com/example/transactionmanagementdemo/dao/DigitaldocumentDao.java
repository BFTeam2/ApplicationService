package com.example.transactionmanagementdemo.dao;


import com.example.transactionmanagementdemo.domain.entity.Digitaldocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class DigitaldocumentDao {
    @Autowired
    SessionFactory sessionFactory;


    public List<Digitaldocument> getAllDigitaldocument(){
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Digitaldocument";
        Query<Digitaldocument> query = session.createQuery(hql);
        return query.getResultList();
    }

}

