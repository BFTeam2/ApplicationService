package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.dao.ApplicationworkflowDao;
import com.example.transactionmanagementdemo.domain.entity.Applicationworkflow;
import com.example.transactionmanagementdemo.domain.request.PurchaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ApplicationworkflowService {
    private final ApplicationworkflowDao applicationworkflowDao;

    @Autowired
    public ApplicationworkflowService(ApplicationworkflowDao applicationworkflowDao) {
        this.applicationworkflowDao = applicationworkflowDao;
    }

    public List getApplicationworkflowByEmployeeID(String employeeID){return applicationworkflowDao.getApplicationworkflowByEmployeeID(employeeID);};

    public List<Applicationworkflow> getApplicationworkflowList(String type) {
        return  applicationworkflowDao.getApplicationworkflowList(type);
    }

    public void reviewApplication(String status, String comment, Integer id) {
        applicationworkflowDao.reviewApplication(status,comment,id);
    }
}

