package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.dao.ApplicationworkflowDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;

@WebMvcTest(ApplicationworkflowService.class)
public class ApplicationworkflowServiceTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean       // this annotation is provided by Spring Boot
    ApplicationworkflowDao applicationworkflowDao;



    @BeforeAll
    public static void prepare() {
        //System.out.println("preparing resource");
    }

    @BeforeEach
    public void init() {
        //System.out.println("before each");
    }



    @Test
    public void testGetApplicationworkflowByEmployeeID_success() throws Exception {
        ApplicationworkflowService service = new ApplicationworkflowService(applicationworkflowDao);
        service.getApplicationworkflowByEmployeeID("0");
        verify(applicationworkflowDao).getApplicationworkflowByEmployeeID("0");
    }

    @Test
    public void testGetApplicationworkflowList_success() throws Exception {
        ApplicationworkflowService service = new ApplicationworkflowService(applicationworkflowDao);
        service.getApplicationworkflowList(null);
        verify(applicationworkflowDao).getApplicationworkflowList(null);
    }


    @Test
    public void testReviewApplication_success() throws Exception {
        ApplicationworkflowService service = new ApplicationworkflowService(applicationworkflowDao);
        service.reviewApplication("refuse","comment",1);
        verify(applicationworkflowDao).reviewApplication("refuse","comment",1);
    }


}