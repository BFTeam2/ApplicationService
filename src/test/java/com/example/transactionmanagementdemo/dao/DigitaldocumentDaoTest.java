package com.example.transactionmanagementdemo.dao;
import com.example.transactionmanagementdemo.domain.entity.Applicationworkflow;
import com.example.transactionmanagementdemo.domain.entity.Digitaldocument;
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
@ActiveProfiles(value = "test")
@SpringBootTest
public class DigitaldocumentDaoTest {
    @Autowired
    DigitaldocumentDao digitaldocumentDao;

    Digitaldocument mockDigitaldocument;

    @BeforeEach
    public void init() {
        //System.out.println("before each");
        Digitaldocument.DigitaldocumentBuilder builder = Digitaldocument.builder().id(1);
        mockDigitaldocument = builder.build();
    }



    @Test
    @Transactional
    public void testGetAllDigitaldocument_success() throws Exception {
        List a = digitaldocumentDao.getAllDigitaldocument();
        assertNotNull(a);
        assertEquals(a.size(), 0);
    }
}
