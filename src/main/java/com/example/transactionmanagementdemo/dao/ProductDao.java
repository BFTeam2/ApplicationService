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
            Query q = session.createQuery("SELECT name FROM Product p WHERE stock_quantity > 0");
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

    public Product getProductWholeObject(String productName, Session session) {
        System.out.println(productName);

        List<Product> productList = null;
        try{

            Query<Product> q = session.createQuery("Select p FROM Product p WHERE name =:productName");
            q.setParameter("productName", productName);
            productList = q.list();
            System.out.println(productList.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList.size() > 0 ? productList.get(0) : null;
    }

    public void reduceStockQuantity(String productName, int quantity, Session session){

        try{
            Query q = session.createQuery("Update Product set stock_quantity = stock_quantity - :quantity WHERE name =:productName");
            q.setParameter("productName", productName);
            q.setParameter("quantity", quantity);
            q.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
