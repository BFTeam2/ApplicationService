package com.example.transactionmanagementdemo.dao;

import com.example.transactionmanagementdemo.domain.entity.Author;
import com.example.transactionmanagementdemo.domain.entity.Product;
import com.example.transactionmanagementdemo.domain.entity.User;
import com.example.transactionmanagementdemo.exception.AuthorSaveFailedException;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserDao {

    @Autowired
    SessionFactory sessionFactory;

    public String register(User user){
        Session session;
        Optional<User> potentialUserSameUsername = null;
        Optional<User> potentialUserSameEmail = null;
        session = sessionFactory.getCurrentSession();
        try{

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            Predicate predicateUsername = cb.equal(root.get("username"),user.getUsername());
            Predicate predicateEmail = cb.equal(root.get("email"),user.getEmail());
            cq.select(root).where(predicateUsername);
            potentialUserSameUsername = session.createQuery(cq).uniqueResultOptional();
            cq.select(root).where(predicateEmail);
            potentialUserSameEmail = session.createQuery(cq).uniqueResultOptional();

            if(potentialUserSameUsername.isPresent() || potentialUserSameEmail.isPresent()) return "user exists.";
            else {
                session.saveOrUpdate(user);
                return "registration success.";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "exception";
    }

    public List getWatchlist(int user_id){
        Session session;
        List<Product> productList = new ArrayList<>();
        try{
            session = sessionFactory.getCurrentSession();

            Query q = session.createQuery("Select distinct u from User u left join u.watchlistProducts Where u.user_id =:user_id");
            q.setParameter("user_id", user_id);
            List<User> list = q.list();
            for(User u: list) {
                productList = u.getWatchlistProducts();
            }
            for(Product product: productList) {
                Hibernate.initialize(product.getUsers());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    public void putToWatchlist(int user_id, int product_id) {
        Session session;
        User user = null;
        Product product = null;
        try{
            session = sessionFactory.getCurrentSession();

            Query q = session.createQuery("from User u Where user_id =:user_id");
            q.setParameter("user_id", user_id);
            List<User> list = q.list();

            if(list.size() > 0) user = list.get(0);

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            session = sessionFactory.getCurrentSession();

            Query q = session.createQuery("from Product p Where product_id =:product_id");
            q.setParameter("product_id", product_id);
            List<Product> list = q.list();

            if(list.size() > 0) product = list.get(0);

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            user.getWatchlistProducts().add(product);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void deleteFromWatchlist(int user_id, int product_id) {
        Session session;
        User user = null;
        Product product = null;
        try{
            session = sessionFactory.getCurrentSession();

            Query q = session.createQuery("from User u Where user_id =:user_id");
            q.setParameter("user_id", user_id);
            List<User> list = q.list();

            if(list.size() > 0) user = list.get(0);

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            session = sessionFactory.getCurrentSession();

            Query q = session.createQuery("from Product p Where product_id =:product_id");
            q.setParameter("product_id", product_id);
            List<Product> list = q.list();

            if(list.size() > 0) product = list.get(0);

        }catch (Exception e){
            e.printStackTrace();
        }

        try{

            user.getWatchlistProducts().remove(product);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List getTop3purchasedProductsNames(int user_id){
        Session session;
        List list = null;
        try{
            session = sessionFactory.getCurrentSession();

            Query q = session.createQuery("SELECT p.name, SUM(op.purchased_quantity) as total_purchased \n" +
                    "FROM OrderProduct op\n" +
                    "JOIN op.orders o\n" +
                    "JOIN op.product p\n" +
                    "WHERE o.user_id = :user_id AND o.order_status <> 'canceled'\n" +
                    "GROUP BY op.product\n" +
                    "ORDER BY total_purchased DESC, p.name ASC");
            q.setParameter("user_id", user_id);
            q.setMaxResults(3);
            list = q.list();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List getTop3MostRecentPurchasedProductsNames(int user_id){
        Session session;
        List list = null;
        try{
            session = sessionFactory.getCurrentSession();

            Query q = session.createQuery("SELECT p.name, o.date_places " +
                    "FROM OrderProduct op " +
                    "JOIN op.orders o " +
                    "JOIN op.product p " +
                    "WHERE o.user_id = :user_id AND o.order_status <> 'canceled' " +
                    "ORDER BY o.date_places DESC, op.order_product_id ASC");
            q.setParameter("user_id", user_id);
            q.setMaxResults(3);
            list = q.list();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
