package com.example.transactionmanagementdemo.AOP;
import com.example.transactionmanagementdemo.exception.NotEnoughInventoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = {NotEnoughInventoryException.class})
    public ResponseEntity handleDemoNotFoundException(NotEnoughInventoryException e){
        return new ResponseEntity("Exception handled: "+e.getMessage(), HttpStatus.OK);
    }
}
