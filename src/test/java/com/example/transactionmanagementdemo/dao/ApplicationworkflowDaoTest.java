package com.example.transactionmanagementdemo.dao;

import com.example.transactionmanagementdemo.dao.ApplicationworkflowDao;
import com.example.transactionmanagementdemo.domain.entity.Applicationworkflow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ActiveProfiles(value = "test")
@SpringBootTest
public class ApplicationworkflowDaoTest {


    @Autowired     // this annotation is provided by Spring Boot
    ApplicationworkflowDao applicationworkflowDao;
    Applicationworkflow mockApplicationworkflow;



    @BeforeEach
    public void init() {
        //System.out.println("before each");
        Applicationworkflow.ApplicationworkflowBuilder builder = Applicationworkflow.builder().id(1);
        mockApplicationworkflow = builder.build();
    }



    @Test
    @Transactional
    public void testGetApplicationWorkflowById_success() throws Exception {
        Applicationworkflow a = applicationworkflowDao.getApplicationWorkflowById(1);
        assertNotNull(a);
        assertEquals(a.getId(), mockApplicationworkflow.getId());
    }

    @Test
    @Transactional
    public void testGetApplicationworkflowList_success() throws Exception {
        List<Applicationworkflow> a = applicationworkflowDao.getApplicationworkflowList("approved");
        assertNotNull(a);
        assertEquals(a.size(), 1);
    }
}
