package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.dao.ApplicationworkflowDao;
import com.example.transactionmanagementdemo.domain.entity.Applicationworkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class ApplicationworkflowService {
    private final ApplicationworkflowDao applicationworkflowDao;
    @Autowired
    public ApplicationworkflowService(ApplicationworkflowDao applicationworkflowDao) {
        this.applicationworkflowDao = applicationworkflowDao;
    }

    public void addApplication(String employee_id) {
        Applicationworkflow applicationworkflow=new Applicationworkflow();
        applicationworkflow.setEmployeeID(employee_id);
        applicationworkflow.setCreateDate(new Timestamp(new Date().getTime()));
        applicationworkflow.setLastModificationDate(new Timestamp(new Date().getTime()));
        applicationworkflow.setStatus("pending");
        applicationworkflowDao.addApplication(applicationworkflow);
    }
    public List getApplicationworkflowByEmployeeID(String employeeID){return applicationworkflowDao.getApplicationworkflowByEmployeeID(employeeID);};

    public List<Applicationworkflow> getApplicationworkflowList(String type) {
        return  applicationworkflowDao.getApplicationworkflowList(type);
    }

    public void reviewApplication(String status, String comment, Integer id) {
        applicationworkflowDao.reviewApplication(status,comment,id);
    }

    public List getApplicationStatusById(String id) {
        return applicationworkflowDao.getApplicationStatus(id);
    }
}

