package com.example.transactionmanagementdemo.controller;

import com.example.transactionmanagementdemo.common.constants.MessageConstant;
import com.example.transactionmanagementdemo.common.domain.ResultDto;
import com.example.transactionmanagementdemo.domain.entity.Applicationworkflow;
import com.example.transactionmanagementdemo.domain.response.ApplicationworkflowResponse;
import com.example.transactionmanagementdemo.security.AuthUserDetail;
import com.example.transactionmanagementdemo.service.ApplicationworkflowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/application")
public class ApplicationworkflowController {

    @Autowired
    private ApplicationworkflowService service;

    @GetMapping(value = "/getApplicationworkflowList")
<<<<<<< Updated upstream
    @ApiOperation(value = "Get ApplicationworkflowList,Default to all employment applications", response = Iterable.class)
    public Map<String,Object> getApplicationworkflowList(@RequestParam(required = false) String status) {
        Map<String,Object> resMap=new HashMap<>();
        List<Applicationworkflow> applicationworkflows=service.getApplicationworkflowList(status);

        resMap.put("applicationworkflows",applicationworkflows);
        resMap.put("msg","success");
        resMap.put("code",200);
        return resMap;
=======
    @ApiOperation(value = "Get ApplicationworkflowList, status: pending, rejected, approved, all", response = Iterable.class)
    public ApplicationworkflowResponse getApplicationworkflowList(@RequestParam String status) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuthUserDetail authUserDetail = (AuthUserDetail) principal;
        System.out.println(authUserDetail.getUsername());
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authUserDetail.getAuthorities();
        String message;
        for(GrantedAuthority grantedAuthority : authorities){
            if(!"HR".equalsIgnoreCase(grantedAuthority.getAuthority())){
                message = "no permission";
                return ApplicationworkflowResponse.builder()
                        .message(message)
                        .build();
            }
        }
        message = "Retrieve Success";
        List<Applicationworkflow> list = service.getApplicationworkflowList(status);
        return ApplicationworkflowResponse.builder()
                .message(message)
                .applicationworkflows(list)
                .build();
>>>>>>> Stashed changes
    }

    @GetMapping(value = "/getApplicationworkflowListById{employee_id}")
    @ApiOperation(value = "Get ApplicationworkflowList by id", response = Iterable.class)
    public Map<String,Object> getApplicationworkflowListById(@PathVariable String employee_id) {
        Map<String,Object> resMap=new HashMap<>();
        List<Applicationworkflow> applicationworkflows=service.getApplicationworkflowByEmployeeID(employee_id);

<<<<<<< Updated upstream
        resMap.put("applicationworkflows",applicationworkflows);
        resMap.put("msg","success");
        resMap.put("code",200);
        return resMap;
=======
    @GetMapping("/getApplicationById/{applicationId}")
    @ApiOperation(value = "get application workflow by application id")
    public ApplicationworkflowResponse getApplicationWorkflowById(@PathVariable int applicationId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuthUserDetail authUserDetail = (AuthUserDetail) principal;
        System.out.println(authUserDetail.getUsername());
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authUserDetail.getAuthorities();
        String message;
        for(GrantedAuthority grantedAuthority : authorities){
            if(!"HR".equalsIgnoreCase(grantedAuthority.getAuthority())){
                message = "no permission";
                return ApplicationworkflowResponse.builder()
                        .message(message)
                        .build();
            }
        }
        message = "Retrieve Success";
        List<Applicationworkflow> list = new ArrayList<>();
        list.add(service.getApplicationWorkflowById(applicationId));
        return ApplicationworkflowResponse.builder()
                .message(message)
                .applicationworkflows(list)
                .build();
>>>>>>> Stashed changes
    }

    @PostMapping("/reviewApplication/{id}")
    @ApiOperation(value = "Review application")
    public Map<String,Object>  reviewApplication(@RequestParam String status,
                                                  @RequestParam(required = false) String comment,
                                                  @PathVariable Integer id) {
        Map<String,Object> resMap= new HashMap<>();
<<<<<<< Updated upstream
        service.reviewApplication(status,comment,id);
=======
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuthUserDetail authUserDetail = (AuthUserDetail) principal;
        System.out.println(authUserDetail.getUsername());
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authUserDetail.getAuthorities();
        String message;
        for(GrantedAuthority grantedAuthority : authorities){
            if(!"HR".equalsIgnoreCase(grantedAuthority.getAuthority())){
                message = "no permission";
                resMap.put("msg","no permission");
                resMap.put("code",500);
            }
        }

        service.reviewApplication(status,comment,applicationId);
>>>>>>> Stashed changes
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

    @PostMapping("/getApplicationStatus")
    @ApiOperation(value = "Get employee's application status")
    public List getApplicationStatusById() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuthUserDetail authUserDetail = (AuthUserDetail) principal;
        String employee_id = authUserDetail.getUser_id().toString();
        return service.getApplicationStatusById(employee_id);
    }
}