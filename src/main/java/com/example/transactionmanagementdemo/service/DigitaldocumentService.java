package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.dao.ApplicationworkflowDao;
import com.example.transactionmanagementdemo.domain.request.PurchaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ApplicationworkflowService {
    private final ApplicationworkflowDao applicationworkflowDao;

    @Autowired
    public ApplicationworkflowService(ApplicationworkflowDao applicationworkflowDao) {
        this.applicationworkflowDao = applicationworkflowDao;
    }

//    @Transactional
    public List getApplicationworkflowByEmployeeID(String employeeID){return applicationworkflowDao.getApplicationworkflowByEmployeeID(employeeID);};
}

