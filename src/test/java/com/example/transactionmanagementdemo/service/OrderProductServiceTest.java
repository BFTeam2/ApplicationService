package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.controller.AdminController;
import com.example.transactionmanagementdemo.dao.OrderProductDao;
import com.example.transactionmanagementdemo.domain.entity.OrderProduct;
import com.example.transactionmanagementdemo.domain.entity.Product;
import com.example.transactionmanagementdemo.domain.entity.User;
import com.example.transactionmanagementdemo.domain.request.PurchaseRequest;
import com.example.transactionmanagementdemo.service.OrderProductService;
import com.example.transactionmanagementdemo.service.OrderService;
import com.example.transactionmanagementdemo.service.ProductService;
import com.example.transactionmanagementdemo.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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