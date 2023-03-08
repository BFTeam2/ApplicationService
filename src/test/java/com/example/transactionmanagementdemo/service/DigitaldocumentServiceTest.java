package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.dao.DigitaldocumentDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;

@WebMvcTest(DigitaldocumentService.class)
public class DigitaldocumentServiceTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean       // this annotation is provided by Spring Boot
    DigitaldocumentDao digitaldocumentDao;


    @BeforeAll
    public static void prepare() {
        //System.out.println("preparing resource");
    }

    @BeforeEach
    public void init() {
        //System.out.println("before each");
    }


    @Test
    public void testGetAllDigitaldocument_success() throws Exception {

        DigitaldocumentService service = new DigitaldocumentService(digitaldocumentDao);
        service.getAllDigitaldocument();
        verify(digitaldocumentDao).getAllDigitaldocument();
    }


}