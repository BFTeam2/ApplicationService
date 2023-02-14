package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.dao.AuthorDao;
import com.example.transactionmanagementdemo.dao.UserDao;
import com.example.transactionmanagementdemo.domain.entity.Author;
import com.example.transactionmanagementdemo.domain.entity.Product;
import com.example.transactionmanagementdemo.domain.entity.User;
import com.example.transactionmanagementdemo.exception.AuthorSaveFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService {
    private final UserDao userDao;
    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public String register(User user) {
        return userDao.register(user);
    }

    @Transactional
    public List getWatchlist(User user) {
        return userDao.getWatchlist(user);
    }

    @Transactional
    public void putToWatchlist(int user_id, int product_id) {
        userDao.putToWatchlist( user_id, product_id);
    }

    @Transactional
    public void deleteFromWatchlist(int user_id, int product_id) {
        userDao.deleteFromWatchlist( user_id, product_id);
    }
}

