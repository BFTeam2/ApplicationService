package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.dao.ApplicationworkflowDao;
import com.example.transactionmanagementdemo.dao.EmployeeRepo;
import com.example.transactionmanagementdemo.domain.entity.Applicationworkflow;
import com.example.transactionmanagementdemo.domain.entity.Employee;
import com.example.transactionmanagementdemo.domain.entity.PersonalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {
    private final StorageService storageService;

    private final EmployeeRepo repository;
    private final ApplicationworkflowDao applicationworkflowDao;

    @Autowired
    public EmployeeService(StorageService storageService, EmployeeRepo repository, ApplicationworkflowDao applicationworkflowDao) {
        this.storageService = storageService;
        this.repository = repository;
        this.applicationworkflowDao = applicationworkflowDao;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Map<String,Object> getEmployeeByUserId(Long id) {
        List<Employee> employees=repository.findEmployeesByUserId(id);
        Set<String> eids=employees.stream().map(employee -> employee.getId()).collect(Collectors.toSet());

        List<Applicationworkflow> applicationworkflows=new ArrayList<>();
        if(eids.size()>0)
            applicationworkflows= applicationworkflowDao.getApplicationworkflowByEmployeeIDs(eids);

        Map<String,Object> resMap=new HashMap<>();
        resMap.put("employees",employees);
        resMap.put("applicationworkflows",applicationworkflows);
        resMap.put("msg","success");
        resMap.put("code",200);
        return resMap;
    }


    public Map<String, Object> getPersonalDocumentByEmployeeId(String employeeId) {
        Map<String,Object> res=new HashMap<>();
        Optional<Employee> optionalEmployee = repository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee e = optionalEmployee.get();
            Map<String, PersonalDocument> docs = e.getPersonalDocuments();
            res.put("docs",docs.values());
            res.put("msg","success");
            res.put("code",200);
        }
        else {
            res.put("msg","No Employee with id:"+ employeeId + " was found");
            res.put("code",251);
        }
        return  res;
    }


    public void saveOrUpdateEmployee(Employee employee) {
        repository.save(employee);
        Applicationworkflow applicationworkflow=new Applicationworkflow();
        applicationworkflow.setEmployeeID(employee.getId());
        applicationworkflow.setCreateDate(new Timestamp(new Date().getTime()));
        applicationworkflow.setLastModificationDate(new Timestamp(new Date().getTime()));
        applicationworkflowDao.addApplication(applicationworkflow);
    }


    public  Map<String,Object>  updateDocumentfile(MultipartFile file,String type,String comment,String employeeId){
        Map<String,Object> res=new HashMap<>();
        Optional<Employee> optionalEmployee = repository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee e = optionalEmployee.get();
            Map<String, PersonalDocument> docs = e.getPersonalDocuments();
            PersonalDocument doc = docs.get(type);
            doc.setTitle(type);
            String path= storageService.uploadFile(file,type);
            doc.setPath(path);
            doc.setCreateDate(sdf.format(new Date()));
            doc.setComment(comment);
            docs.put(type, doc);
            e.setPersonalDocuments(docs);
            repository.save(e);

            res.put("msg","success");
            res.put("code",200);
        }
        else {
            res.put("msg","No Employee with id:"+ employeeId + " was found");
            res.put("code",251);
        }
        return  res;
    }

    public byte[] downloadFile(String employeeId,String fileName) {
        Optional<Employee> optionalEmployee = repository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee e = optionalEmployee.get();
            Map<String, PersonalDocument> docs = e.getPersonalDocuments();
            boolean isExist=false;
            for(PersonalDocument personalDocument:docs.values()){
                if(fileName.equals(personalDocument.getPath())){
                    isExist=true;
                    break;
                }
            }
            if(isExist)
                return storageService.downloadFile(fileName);
        }
        return new byte[0];

    }
}
