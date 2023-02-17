package com.example.transactionmanagementdemo.dao;

import com.example.transactionmanagementdemo.domain.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<String> getAllProductNamesAdmin(){
        Session session;
        List<String> productList = null;
        try{
            session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("SELECT name FROM Product p");
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
        List<Product> productList = null;
        try{

            Query<Product> q = session.createQuery("Select p FROM Product p WHERE name =:productName");
            q.setParameter("productName", productName);
            productList = q.list();

        }catch (Exception e){
            e.printStackTrace();
        }
        return productList.size() > 0 ? productList.get(0) : null;
    }

    public Product getProductWholeObject(String productName) {
        Session session;
        List<Product> productList = null;
        try{
            session = sessionFactory.getCurrentSession();
            Query<Product> q = session.createQuery("Select p FROM Product p WHERE name =:productName");
            q.setParameter("productName", productName);
            productList = q.list();

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
    public void reduceStockQuantity(String productName, int quantity){
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("Update Product set stock_quantity = stock_quantity - :quantity WHERE name =:productName");
            q.setParameter("productName", productName);
            q.setParameter("quantity", quantity);
            q.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void increaseStockQuantity(String productName, int quantity){
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("Update Product set stock_quantity = stock_quantity + :quantity WHERE name =:productName");
            q.setParameter("productName", productName);
            q.setParameter("quantity", quantity);
            q.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addProduct(Product product){
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(product);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getMostProfitableProduct() {
        Session session;
        try{
            session = sessionFactory.getCurrentSession();

            Query<String> q = session.createQuery("SELECT op.product.name, SUM((op.execution_retail_price - op.execution_wholesale_price) * op.purchased_quantity) as profit " +
                    "FROM OrderProduct op " +
                    "JOIN op.orders o " +
                    "WHERE o.order_status = 'completed' " +
                    "GROUP BY op.product " +
                    "ORDER BY profit DESC, op.product.product_id ASC");
            q.setMaxResults(1);
            Object result = q.uniqueResult();
            if (result == null) {
                return null;
            }
            Object[] row = (Object[]) result;
            String productName = (String) row[0];
            return productName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public List getTop3MostSoldProducts() {
        Session session;
        List list = null;
        try{
            session = sessionFactory.getCurrentSession();

            Query q = session.createQuery("SELECT p.name, SUM(op.purchased_quantity) AS total_purchased_quantity " +
                    "FROM OrderProduct op " +
                    "JOIN op.orders o " +
                    "JOIN op.product p " +
                    "WHERE o.order_status = 'completed' " +
                    "GROUP BY op.product " +
                    "ORDER BY total_purchased_quantity DESC");
            q.setMaxResults(3);
            list = q.list();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public long getTotalAmountProductsSold(){
        Session session;
        long result = 0;
        try{
            session = sessionFactory.getCurrentSession();

            Query<Long> q = session.createQuery(
                    "SELECT SUM(op.purchased_quantity) AS total_amount " +
                    "FROM OrderProduct op " +
                    "JOIN op.orders o " +
                    "WHERE o.order_status = 'completed'");

            result = q.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
