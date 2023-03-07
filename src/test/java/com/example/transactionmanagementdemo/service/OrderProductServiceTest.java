package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.dao.OrderProductDao;
import com.example.transactionmanagementdemo.domain.request.PurchaseRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
@WebMvcTest(OrderProductService.class)
public class OrderProductServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean       // this annotation is provided by Spring Boot
    OrderProductDao orderProductDao;

    @BeforeAll
    public static void prepare() {
        //System.out.println("preparing resource");
    }

    @BeforeEach
    public void init() {
        //System.out.println("before each");
    }

    @Test
    public void testAddOrderProduct_success() throws Exception {

        OrderProductService service = new OrderProductService(orderProductDao);
        PurchaseRequest r = new PurchaseRequest();
        service.addOrderProduct(r, 1);
        verify(orderProductDao).addOrderProduct(r, 1);
    }
}