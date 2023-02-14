package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.dao.AuthorDao;
import com.example.transactionmanagementdemo.dao.ProductDao;
import com.example.transactionmanagementdemo.domain.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional
    public List<String> getAllProductNames(){
        return productDao.getAllProductNames();
    }

    @Transactional
    public List getProduct(String name) {return productDao.getProduct(name);}
}
