package com.example.transactionmanagementdemo.controller;

import com.example.transactionmanagementdemo.domain.request.PurchaseRequest;
import com.example.transactionmanagementdemo.service.OrderProductService;
import com.example.transactionmanagementdemo.service.OrderService;
import com.example.transactionmanagementdemo.service.ProductService;
import com.example.transactionmanagementdemo.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean       // this annotation is provided by Spring Boot
    ProductService productService;
    @MockBean       // this annotation is provided by Spring Boot
    OrderService orderService;
    @MockBean       // this annotation is provided by Spring Boot
    OrderProductService orderProductService;

    @BeforeAll
    public static void prepare() {
        //System.out.println("preparing resource");
    }

    @BeforeEach
    public void init() {
        //System.out.println("before each");
    }

    @Test
    public void testPurchase_success() throws Exception {

        PurchaseController controller = new PurchaseController(productService, orderService, orderProductService);
        PurchaseRequest r = new PurchaseRequest();
        controller.purchase(1, r);
        verify(orderProductService).addOrderProduct(r, 1);
    }
}
