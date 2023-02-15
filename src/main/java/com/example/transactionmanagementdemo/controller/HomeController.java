package com.example.transactionmanagementdemo.controller;
import com.example.transactionmanagementdemo.domain.entity.Product;

import com.example.transactionmanagementdemo.service.OrderService;
import com.example.transactionmanagementdemo.service.ProductService;
import com.example.transactionmanagementdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.PAForUserEnc;

import java.util.List;

@RestController
public class HomeController {
    public final ProductService productService;
    public final OrderService orderService;
    @Autowired
    public HomeController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/allProducts")
    public List<String> getAllProductNames(){
        return productService.getAllProductNames();
    }
    @GetMapping("/admin/allProducts")
    public List<String> getAllProductNamesAdmin(){
        return productService.getAllProductNamesAdmin();
    }

    @GetMapping("/product/{name}")
    public List getProduct(@PathVariable String name) {
        return productService.getProduct(name);
    }
    @GetMapping("/admin/product/{name}")
    public Product getProductWholeObject(@PathVariable String name) {
        return productService.getProductWholeObject(name);
    }

    @GetMapping("/allOrders/{user_id}")
    public List getAllOrders(@PathVariable int user_id){
        return orderService.getAllOrders(user_id);
    }
    @GetMapping("/admin/allOrders")
    public List getAllOrdersAdmin(){
        return orderService.getAllOrdersAdmin();
    }

    @PostMapping("/cancelOrder/{user_id}")
    public void cancelOrder(@PathVariable int user_id, @RequestParam int order_id){
        orderService.cancelOrder(user_id, order_id);
    }
}
