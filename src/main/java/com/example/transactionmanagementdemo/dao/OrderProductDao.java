package com.example.transactionmanagementdemo.dao;


import com.example.transactionmanagementdemo.domain.entity.Orders;
import com.example.transactionmanagementdemo.domain.entity.OrderProduct;
import com.example.transactionmanagementdemo.domain.entity.Product;
import com.example.transactionmanagementdemo.domain.request.PurchaseRequest;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderProductDao {
    @Autowired
    SessionFactory sessionFactory;

    public void addOrderProduct(PurchaseRequest purchaseRequest, int user_id){
        Session session;

        try{
            session = sessionFactory.getCurrentSession();
            Orders newOrder = new Orders();
            newOrder.setUser_id(user_id);
            newOrder.setOrder_status("processing");
            newOrder.setDate_places(new Timestamp(System.currentTimeMillis()));

            for(int i = 0; i < purchaseRequest.getProductNames().size(); i++) {
                ProductDao productDao = new ProductDao();
                String productName = purchaseRequest.getProductNames().get(i);
                int quantity = purchaseRequest.getPuductQuantity().get(i);
                Product product = productDao.getProductWholeObject(productName, session);

                // create orderProduct
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setProduct(product);
                orderProduct.setOrders(newOrder);
                orderProduct.setPurchased_quantity(quantity);
                orderProduct.setExecution_retail_price(product.getRetail_price());
                orderProduct.setExecution_wholesale_price(product.getWholesale_price());

                // update orders
                newOrder.getOrderProducts().add(orderProduct);
                session.persist(newOrder);

                // update product
                productDao.reduceStockQuantity(productName, quantity, session);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

