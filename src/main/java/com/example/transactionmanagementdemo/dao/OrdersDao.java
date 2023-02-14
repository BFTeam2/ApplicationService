package com.example.transactionmanagementdemo.dao;


import com.example.transactionmanagementdemo.domain.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class OrdersDao {
    @Autowired
    SessionFactory sessionFactory;

    public List getAllOrders(int user_id){
        Session session;
        List<Orders> ordersList = null;
        try{
            session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("SELECT order_id, order_status, date_places FROM Orders WHERE user_id =:user_id");
            q.setParameter("user_id", user_id);
            ordersList = q.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ordersList;
    }
}
