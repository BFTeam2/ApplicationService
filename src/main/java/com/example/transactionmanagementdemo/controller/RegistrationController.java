package com.example.transactionmanagementdemo.controller;


import com.example.transactionmanagementdemo.domain.entity.User;
import com.example.transactionmanagementdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    public final UserService userService;
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @PutMapping("/registration")
    public String register(@RequestBody User user){
        return userService.register(user);
    }
}
