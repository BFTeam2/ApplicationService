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
//@RequestMapping("/application")
public class ApplicationworkflowController {

    @Autowired
    private ApplicationworkflowService service;

    @Autowired
    public ApplicationworkflowController(ApplicationworkflowService service) {
        this.service = service;
    }
    @GetMapping(value = "/getApplicationworkflowList")
    @ApiOperation(value = "Get ApplicationworkflowList, status: pending, rejected, approved, all", response = Iterable.class)
    public List<Applicationworkflow> getApplicationworkflowList(@RequestParam String status) {
        return service.getApplicationworkflowList(status);
    }

//    not used
//    @GetMapping(value = "/getApplicationworkflowListById/{employee_id}")
//    @ApiOperation(value = "Get ApplicationworkflowList by id", response = Iterable.class)
//    public Map<String,Object> getApplicationworkflowListById(@PathVariable String employee_id) {
//        Map<String,Object> resMap=new HashMap<>();
//        List<Applicationworkflow> applicationworkflows=service.getApplicationworkflowByEmployeeID(employee_id);
//
//        resMap.put("applicationworkflows",applicationworkflows);
//        resMap.put("msg","success");
//        resMap.put("code",200);
//        return resMap;
//    }

    @GetMapping("/getApplicationById/{applicationId}")
    @ApiOperation(value = "get application workflow by application id")
    public Applicationworkflow getApplicationWorkflowById(@PathVariable int applicationId){
        return service.getApplicationWorkflowById(applicationId);
    }

    @PostMapping("/reviewApplication/{applicationId}")
    @ApiOperation(value = "modify application status")
    public Map<String,Object>  reviewApplication(@RequestParam String status,
                                                  @RequestParam String comment,
                                                  @PathVariable Integer applicationId) {
        Map<String,Object> resMap= new HashMap<>();
        service.reviewApplication(status,comment,applicationId);
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

}