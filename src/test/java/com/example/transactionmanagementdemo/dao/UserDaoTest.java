//package com.example.transactionmanagementdemo.dao;
//
//
//import com.example.transactionmanagementdemo.controller.AdminController;
//import com.example.transactionmanagementdemo.dao.OrderProductDao;
//import com.example.transactionmanagementdemo.dao.UserDao;
//import com.example.transactionmanagementdemo.domain.entity.OrderProduct;
//import com.example.transactionmanagementdemo.domain.entity.Product;
//import com.example.transactionmanagementdemo.domain.entity.User;
//import com.example.transactionmanagementdemo.domain.request.PurchaseRequest;
//import com.example.transactionmanagementdemo.service.OrderProductService;
//import com.example.transactionmanagementdemo.service.OrderService;
//import com.example.transactionmanagementdemo.service.ProductService;
//import com.example.transactionmanagementdemo.service.UserService;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
////@WebMvcTest(UserDao.class)
//@ActiveProfiles(value = "test")     // using application-test.properties
//@SpringBootTest
//public class UserDaoTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired       // this annotation is provided by Spring Boot
//    UserDao userDao;
//
//    User mockUser;
//
//    @BeforeAll
//    public static void prepare() {
//        //System.out.println("preparing resource");
//    }
//
//    @BeforeEach
//    public void init() {
//        //System.out.println("before each");
//        mockUser = User.builder().user_id(1).build();
//    }
//
//    @Test
//    @Transactional
//    public void testRegister_success() throws Exception {
//
//        String result = userDao.register(mockUser);
//        assertNotNull(result);
//        assertEquals("registration success.", result);
//    }
//
//
//}