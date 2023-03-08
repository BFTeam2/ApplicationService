package com.example.transactionmanagementdemo.controller;

import com.example.transactionmanagementdemo.domain.entity.Applicationworkflow;
import com.example.transactionmanagementdemo.service.ApplicationworkflowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/application")
public class ApplicationworkflowController {

    @Autowired
    private ApplicationworkflowService service;

    @GetMapping(value = "/getApplicationworkflowList")
    @ApiOperation(value = "Get ApplicationworkflowList,Default to all employment applications", response = Iterable.class)
    public Map<String,Object> getApplicationworkflowList(@RequestParam(required = false) String status) {
        Map<String,Object> resMap=new HashMap<>();
        List<Applicationworkflow> applicationworkflows=service.getApplicationworkflowList(status);

        resMap.put("applicationworkflows",applicationworkflows);
        resMap.put("msg","success");
        resMap.put("code",200);
        return resMap;
    }

    @GetMapping(value = "/getApplicationworkflowListById{employee_id}")
    @ApiOperation(value = "Get ApplicationworkflowList by id", response = Iterable.class)
    public Map<String,Object> getApplicationworkflowListById(@PathVariable String employee_id) {
        Map<String,Object> resMap=new HashMap<>();
        List<Applicationworkflow> applicationworkflows=service.getApplicationworkflowByEmployeeID(employee_id);

        resMap.put("applicationworkflows",applicationworkflows);
        resMap.put("msg","success");
        resMap.put("code",200);
        return resMap;
    }

    @PostMapping("/reviewApplication/{id}")
    @ApiOperation(value = "Review application")
    public Map<String,Object>  reviewApplication(@RequestParam String status,
                                                  @RequestParam(required = false) String comment,
                                                  @PathVariable Integer id) {
        Map<String,Object> resMap= new HashMap<>();
        service.reviewApplication(status,comment,id);
        resMap.put("msg","success");
        resMap.put("code",200);
        return  resMap;
    }

    @PostMapping("/submitApplication/{employee_id}")
    @ApiOperation(value = "submit application")
    public Map<String,Object> addApplication(@PathVariable String employee_id) {
        Map<String,Object> res=new HashMap<>();
        service.addApplication(employee_id);
        res.put("msg","success");
        res.put("code",200);
        return res;
    }

    @PostMapping("/getApplicationStatus/{employee_id}")
    @ApiOperation(value = "Get employee's application status")
    public List getApplicationStatusById(@PathVariable("employee_id") String employee_id) {
        return service.getApplicationStatusById(employee_id);
    }
}