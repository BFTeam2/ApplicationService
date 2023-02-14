package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.dao.OrderProductDao;
import com.example.transactionmanagementdemo.dao.OrdersDao;
import com.example.transactionmanagementdemo.domain.request.PurchaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderProductService {
    private final OrderProductDao orderProductDao;

    @Autowired
    public OrderProductService(OrderProductDao orderProductDao) {
        this.orderProductDao = orderProductDao;
    }

    @Transactional
    public void addOrderProduct(PurchaseRequest purchaseRequest, int user_id){ orderProductDao.addOrderProduct(purchaseRequest, user_id);};
}

