package com.example.transactionmanagementdemo.controller;
import com.example.transactionmanagementdemo.domain.entity.Product;

import com.example.transactionmanagementdemo.service.OrderService;
import com.example.transactionmanagementdemo.service.ProductService;
import com.example.transactionmanagementdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("home")
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

    @GetMapping("/product/{name}")
    public List getProduct(@PathVariable String name) {
        return productService.getProduct(name);
    }

    @GetMapping("/allOrders/{user_id}")
    public List getAllOrders(@PathVariable int user_id){
        return orderService.getAllOrders(user_id);
    }
}
