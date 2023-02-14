package com.example.transactionmanagementdemo.dao;

import com.example.transactionmanagementdemo.domain.entity.Author;
import com.example.transactionmanagementdemo.domain.entity.Product;
import com.example.transactionmanagementdemo.exception.AuthorSaveFailedException;
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
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    @Autowired
    SessionFactory sessionFactory;

    public List<String> getAllProductNames(){
        Session session;
        List<String> productList = null;
        try{
            session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("SELECT Product.name FROM Product WHERE stock_quantity > 0");
            productList = q.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    public List getProduct(String productName) {
        Session session;
        List productList = null;
        try{
            session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("SELECT name, description, retail_price FROM Product WHERE name =:productName");
            q.setParameter("productName", productName);
            productList = q.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
}
