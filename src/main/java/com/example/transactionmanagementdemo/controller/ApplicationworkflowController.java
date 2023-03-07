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
@RequestMapping("/ApplicationReview")
public class ApplicationworkflowController {

    @Autowired
    private ApplicationworkflowService service;

    @GetMapping(value = "/getApplicationworkflowList")
    @ApiOperation(value = "Get ApplicationworkflowList,Default to all employment applications", response = Iterable.class)
    public Map<String,Object> getApplicationworkflowList(@RequestParam(required = false) String type) {
        Map<String,Object> resMap=new HashMap<>();
        List<Applicationworkflow> applicationworkflows=service.getApplicationworkflowList(type);

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
}