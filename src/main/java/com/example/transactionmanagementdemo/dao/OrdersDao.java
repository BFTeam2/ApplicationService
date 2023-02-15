package com.example.transactionmanagementdemo.dao;


import com.example.transactionmanagementdemo.domain.entity.OrderProduct;
import com.example.transactionmanagementdemo.domain.entity.Orders;
import com.example.transactionmanagementdemo.domain.entity.Product;
import org.hibernate.Hibernate;
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

    public List getAllOrdersAdmin(){
        Session session;
        List<Orders> ordersList = null;
        try{
            session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("SELECT o FROM Orders o");

            ordersList = q.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ordersList;
    }

    public void cancelOrder(int order_id) {
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("Update Orders Set order_status = 'canceled' WHERE order_id =:order_id and order_status = 'processing'");

            q.setParameter("order_id", order_id);
            int orderExist = q.executeUpdate();
            if(orderExist == 0) return;

            q = session.createQuery("Select o From Orders o WHERE order_id =:order_id");

            q.setParameter("order_id", order_id);

            List<Orders> ordersList = q.list();
            Orders orders = ordersList.get(0);
            Hibernate.initialize(orders.getOrderProducts());
            for(OrderProduct orderProduct:orders.getOrderProducts()) {
                Product product = orderProduct.getProduct();
                product.setStock_quantity(product.getStock_quantity() + orderProduct.getPurchased_quantity());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void completeOrder(int order_id){
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("Update Orders Set order_status = 'completed' WHERE order_id =:order_id and order_status = 'processing'");

        q.setParameter("order_id", order_id);
        int orderExist = q.executeUpdate();
    }
}
