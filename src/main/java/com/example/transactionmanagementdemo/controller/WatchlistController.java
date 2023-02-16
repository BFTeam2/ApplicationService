package com.example.transactionmanagementdemo.controller;

import com.example.transactionmanagementdemo.domain.entity.Product;
import com.example.transactionmanagementdemo.domain.entity.User;
import com.example.transactionmanagementdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("user")
public class WatchlistController {
    public final UserService userService;
    @Autowired
    public WatchlistController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/watchlist/{user_id}")
    public List getWatchlist(@PathVariable int user_id){
        return userService.getWatchlist(user_id);
    }

    @PutMapping(path="/watchlist/add/{user_id}", params = "product_id")
    public void putToWatchlist( @PathVariable int user_id, @RequestParam int product_id){
        userService.putToWatchlist(user_id, product_id);
    }

    @DeleteMapping(path="/watchlist/delete/{user_id}", params = "product_id")
    public void deleteFromWatchlist( @PathVariable int user_id, @RequestParam int product_id){
        userService.deleteFromWatchlist(user_id, product_id);
    }
}
