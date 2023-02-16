package com.example.transactionmanagementdemo.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCuts {
    @Pointcut("execution(* com.example.transactionmanagementdemo.dao.OrdersDao.*Order(..))")
    public void changeOrderStatus(){}

    @Pointcut("execution(* com.example.transactionmanagementdemo.controller.PurchaseController.purchase(..))")
    public void purchase(){}
}

