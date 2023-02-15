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
@RequestMapping("admin")
public class AdminController {
    public final ProductService productService;
    public final OrderService orderService;

    @Autowired
    public AdminController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/allProducts")
    public List<String> getAllProductNamesAdmin() {
        return productService.getAllProductNamesAdmin();
    }

    @GetMapping("/product/{name}")
    public Product getProductWholeObject(@PathVariable String name) {
        return productService.getProductWholeObject(name);
    }

    @GetMapping("/allOrders")
    public List getAllOrdersAdmin() {
        return orderService.getAllOrdersAdmin();
    }

    @PutMapping("addProduct")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PostMapping("sellProduct/{name}")
    public void reduceStockQuantity(@PathVariable String name, @RequestParam int quantity){productService.reduceStockQuantity(name, quantity);}

    @PostMapping("/cancelOrder/{order_id}")
    public void cancelOrder(@PathVariable int order_id){
        orderService.cancelOrder(order_id);
    }

    @PostMapping("/completeOrder/{order_id}")
    public void completeOrder(@PathVariable int order_id){
        orderService.completeOrder(order_id);
    }
}
