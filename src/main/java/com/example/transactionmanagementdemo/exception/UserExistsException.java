package com.example.transactionmanagementdemo.exception;

public class UserExistsException extends RuntimeException{


    public UserExistsException(String message){
        super(String.format(message));
    }
}