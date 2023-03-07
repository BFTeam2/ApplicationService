package com.example.transactionmanagementdemo.controller;
import com.example.transactionmanagementdemo.domain.entity.Orders;
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
@RequestMapping("user")
public class HomeController {
    public final ProductService productService;
    public final OrderService orderService;
    public final UserService userService;
    @Autowired
    public HomeController(ProductService productService, OrderService orderService, UserService userService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/allProducts")
    public List<String> getAllProductNames(){
        return productService.getAllProductNames();
    }


    @GetMapping("/product/{name}")
    public List getProduct(@PathVariable String name) {
        return productService.getProduct(name);
    }


    @GetMapping("/order/{order_id}")
    public List getOrderById(@PathVariable int order_id){
        return orderService.getOrderById(order_id);
    }

    @GetMapping("/allOrders/{user_id}")
    public List getAllOrders(@PathVariable int user_id){
        return orderService.getAllOrders(user_id);
    }

    @PostMapping("/cancelOrder/{order_id}")
    public void cancelOrder(@PathVariable int order_id){
        orderService.cancelOrder(order_id);
    }

    @GetMapping("/top3MostPurchase/{user_id}")
    public List getTop3purchasedProductsNames(@PathVariable int user_id) {
        return userService.getTop3purchasedProductsNames(user_id);
    }

    @GetMapping("/top3RecentPurchase/{user_id}")
    public List getTop3MostRecentPurchasedProductsNames(@PathVariable int user_id) {
        return userService.getTop3MostRecentPurchasedProductsNames(user_id);
    }
}
