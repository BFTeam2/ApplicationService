package com.example.transactionmanagementdemo.controller;
import com.example.transactionmanagementdemo.dao.ApplicationworkflowDao;
import com.example.transactionmanagementdemo.service.ApplicationworkflowService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;

@WebMvcTest(ApplicationworkflowService.class)
public class ApplicationWorkflowControllerTest {



    @MockBean       // this annotation is provided by Spring Boot
    ApplicationworkflowService applicationworkflowService;


    @Test
    public void testGetApplicationworkflowList_success() throws Exception {
        ApplicationworkflowController controller = new ApplicationworkflowController(applicationworkflowService);
        controller.getApplicationworkflowList("reject");
        verify(applicationworkflowService).getApplicationworkflowList("reject");
    }

    @Test
    public void testReviewApplication_success() throws Exception {
        ApplicationworkflowController controller = new ApplicationworkflowController(applicationworkflowService);
        controller.reviewApplication("reject", "no", 0);
        verify(applicationworkflowService).reviewApplication("reject", "no", 0);
    }

    @Test
    public void testAddApplication_success() throws Exception {
        ApplicationworkflowController controller = new ApplicationworkflowController(applicationworkflowService);
        controller.addApplication("0");
        verify(applicationworkflowService).addApplication("0");
    }

}
