package com.example.transactionmanagementdemo.dao;

import com.example.transactionmanagementdemo.domain.entity.Author;
import com.example.transactionmanagementdemo.domain.entity.Product;
import com.example.transactionmanagementdemo.domain.entity.User;
import com.example.transactionmanagementdemo.exception.AuthorSaveFailedException;
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

    public List getWatchlist(User user){
        Session session;
        List productList = new ArrayList<>();
        try{
            session = sessionFactory.getCurrentSession();

            Query q = session.createQuery("from User u left join fetch u.watchlistProducts Where u.user_id =:user_id");
            q.setParameter("user_id", user.getUser_id());
            List<User> list = q.list();
            System.out.println(list.size());
            for(User u: list) {
                productList.add(u.getWatchlistProducts());
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
}
