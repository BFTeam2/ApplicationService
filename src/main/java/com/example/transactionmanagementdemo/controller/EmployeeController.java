package com.example.transactionmanagementdemo.controller;


import com.example.transactionmanagementdemo.domain.entity.Employee;
import com.example.transactionmanagementdemo.service.ApplicationworkflowService;
import com.example.transactionmanagementdemo.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;
    private final ApplicationworkflowService applicationworkflowService;

    @Autowired
    public EmployeeController(EmployeeService service, ApplicationworkflowService applicationworkflowService) {
        this.service = service;
        this.applicationworkflowService = applicationworkflowService;
    }

    @GetMapping(value = "/getEmployeeByUserId/{id}")
    @ApiOperation(value = "Find Employee and Applicationworkflows By UserId", response = Iterable.class)
    public Map<String,Object>  getEmployeeByUserId(@PathVariable Long id) {
        Map<String,Object> resMap=service.getEmployeeByUserId(id);
        return resMap;
    }

    @GetMapping(value = "/getPersonalDocumentByEmployeeId/{id}")
    @ApiOperation(value = "Find PersonalDocument By EmployeeId", response = Iterable.class)
    public Map<String,Object>  getPersonalDocumentByEmployeeId(@PathVariable String id) {
        Map<String,Object> resMap=service.getPersonalDocumentByEmployeeId(id);
        return resMap;
    }


    @PostMapping("/saveOrUpdateEmployee")
    @ApiOperation(value = "Save or update employee")
    public Map<String,Object> saveOrUpdateEmployee(@RequestBody Employee employee) {
        Map<String,Object> res=new HashMap<>();
        service.saveOrUpdateEmployee(employee);
        res.put("msg","success");
        res.put("code",200);
        return res;
    }

    @PostMapping("/submitApplication/{employee_id}")
    @ApiOperation(value = "submit application")
    public Map<String,Object> addApplication(@PathVariable String employee_id) {
        Map<String,Object> res=new HashMap<>();
        applicationworkflowService.addApplication(employee_id);
        res.put("msg","success");
        res.put("code",200);
        return res;
    }


    @PostMapping("/updateProfile/{employeeId}")
    @ApiOperation(value = "Update Employee PersonalDocument")
    public Map<String,Object>  updateDocumentfile(@RequestParam(value = "file") MultipartFile file,
                                 @RequestParam String type,
                                 @RequestParam(required = false) String comment,
                                 @PathVariable String employeeId) {
        Map<String,Object> res= service.updateDocumentfile(file,type,comment,employeeId);
        return  res;
    }


    @GetMapping("/download/{employeeId}/{fileName}")
    @ApiOperation(value = "download personal document")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("employeeId") String employeeId,@PathVariable("fileName") String fileName) {
        byte[] data = service.downloadFile(employeeId,fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }


    @PostMapping("/getApplicationStatus/{employee_id}")
    @ApiOperation(value = "Get employee's application status")
    public List getApplicationStatusById(@PathVariable("employee_id") String employee_id) {
        return service.getApplicationStatusById(employee_id);
    }
}
