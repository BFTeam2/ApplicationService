package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.dao.ProductDao;
import com.example.transactionmanagementdemo.domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<String> getAllProductNamesAdmin(){
        return productDao.getAllProductNamesAdmin();
    }

    @Transactional
    public List getProduct(String name) {return productDao.getProduct(name);}

    @Transactional
    public Product getProductWholeObject(String productName) {return productDao.getProductWholeObject(productName);}

    @Transactional
    public void reduceStockQuantity(String productName, int quantity){
         productDao.reduceStockQuantity(productName,quantity);
    }

    @Transactional
    public void addProduct(Product product) {productDao.addProduct(product);}

    @Transactional
    public String getMostProfitableProduct() {return productDao.getMostProfitableProduct();}
    @Transactional
    public List getTop3MostSoldProducts() {return productDao.getTop3MostSoldProducts();}

    @Transactional
    public long getTotalAmountProductsSold() {return productDao.getTotalAmountProductsSold();}
}
