package com.example.transactionmanagementdemo.dao;

import com.example.transactionmanagementdemo.domain.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String> {
    List<Employee> findEmployeesByUserId(Long userId);

}