package com.example.transactionmanagementdemo.controller;

import com.example.transactionmanagementdemo.domain.request.PurchaseRequest;
import com.example.transactionmanagementdemo.service.OrderProductService;
import com.example.transactionmanagementdemo.service.OrderService;
import com.example.transactionmanagementdemo.service.ProductService;
import com.example.transactionmanagementdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {
    public final ProductService productService;
    public final OrderService orderService;
    public final OrderProductService orderProductService;
    @Autowired
    public PurchaseController(ProductService productService, OrderService orderService, OrderProductService orderProductService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }

    @PostMapping("/purchase/{user_id}")
    public void purchase(@PathVariable int user_id, @RequestBody PurchaseRequest purchaseRequest){

        orderProductService.addOrderProduct(purchaseRequest, user_id);
    }
}