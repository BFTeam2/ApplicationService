package com.example.transactionmanagementdemo.dao;

import com.example.transactionmanagementdemo.domain.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import java.util.Set;

@Repository
public class WatchlistDao {
    @Autowired
    SessionFactory sessionFactory;

    public List getWatchlist(User user){
        Session session;
        List<String> productList = null;
        try{
            session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("SELECT User.watchlistProducts FROM User");
            productList = q.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
}
