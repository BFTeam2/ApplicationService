package com.example.transactionmanagementdemo.controller;

import com.example.transactionmanagementdemo.domain.entity.Product;
import com.example.transactionmanagementdemo.domain.entity.User;
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
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean       // this annotation is provided by Spring Boot
    ProductService productService;
    @MockBean       // this annotation is provided by Spring Boot
    OrderService orderService;
    @MockBean       // this annotation is provided by Spring Boot
    UserService userService;
    @BeforeAll
    public static void prepare() {
        //System.out.println("preparing resource");
    }

    @BeforeEach
    public void init() {
        //System.out.println("before each");
    }

    @Test
    public void testAddProduct_success() throws Exception {
        Product product = Product.builder().build();
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/addProduct")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new Gson().toJson(product))  //Request Body
//                        .accept(MediaType.A@Test
//    public void testCancelOrder_success() throws Exception {
//
//        AdminController controller = new AdminController(productService, orderService, userService);
//        controller.cancelOrder(1);
//        verify(orderService).cancelOrder(1);
//    }PPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn();
        AdminController controller = new AdminController(productService, orderService, userService);
        controller.addProduct(product);
        verify(productService).addProduct(product);
    }

    @Test
    public void testReduceStockQuantity_success() throws Exception {

        AdminController controller = new AdminController(productService, orderService, userService);
        controller.reduceStockQuantity("product", 1);
        verify(productService).reduceStockQuantity("product", 1);
    }



    @Test
    public void testCompleteOrder_success() throws Exception {

        AdminController controller = new AdminController(productService, orderService, userService);
        controller.completeOrder(1);
        verify(orderService).completeOrder(1);
    }
}