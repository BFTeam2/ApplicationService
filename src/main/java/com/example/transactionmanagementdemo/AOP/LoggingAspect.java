package com.example.transactionmanagementdemo.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    @Around("com.example.transactionmanagementdemo.AOP.PointCuts.changeOrderStatus()")
    public void logStartAndEndTimeOrderStatus(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        logger.info("Log Start time: " + System.currentTimeMillis());
        proceedingJoinPoint.proceed();
        logger.info("Log End time: " + System.currentTimeMillis());
    }
    @Around("com.example.transactionmanagementdemo.AOP.PointCuts.purchase()")
    public void logStartAndEndTimePurchase(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        logger.info("Log Start time: " + System.currentTimeMillis());
        proceedingJoinPoint.proceed();
        logger.info("Log End time: " + System.currentTimeMillis());
    }

}