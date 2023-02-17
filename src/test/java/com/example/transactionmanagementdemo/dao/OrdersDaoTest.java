//package com.example.transactionmanagementdemo.dao;
//
//import com.example.transactionmanagementdemo.controller.AdminController;
//import com.example.transactionmanagementdemo.dao.OrderProductDao;
//import com.example.transactionmanagementdemo.dao.OrdersDao;
//import com.example.transactionmanagementdemo.dao.UserDao;
//import com.example.transactionmanagementdemo.domain.entity.OrderProduct;
//import com.example.transactionmanagementdemo.domain.entity.Orders;
//import com.example.transactionmanagementdemo.domain.entity.Product;
//import com.example.transactionmanagementdemo.domain.entity.User;
//import com.example.transactionmanagementdemo.domain.request.PurchaseRequest;
//import com.example.transactionmanagementdemo.service.OrderProductService;
//import com.example.transactionmanagementdemo.service.OrderService;
//import com.example.transactionmanagementdemo.service.ProductService;
//import com.example.transactionmanagementdemo.service.UserService;
//import com.google.gson.Gson;
//import com.mysql.cj.Session;
//import com.mysql.cj.xdevapi.SessionFactory;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@WebMvcTest(OrdersDao.class)
//public class OrdersDaoTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Mock
//    private SessionFactory sessionFactory;
//    @MockBean       // this annotation is provided by Spring Boot
//    OrdersDao ordersDao;
//    @Mock
//    private Session session;
//
//    @BeforeAll
//    public static void prepare() {
//        //System.out.println("preparing resource");
//    }
//
//    @BeforeEach
//    public void setup() {
////        when(sessionFactory.getCurrentSession()).thenReturn(session);
//        Orders order = Orders.builder().order_id(1).build();
//
//    }
//
//    @Test
//    public void testGetAllOrders() {
//        OrdersDao od = new OrdersDao();
//        List list = ordersDao.getAllOrders(1);
//        assertNotNull(list);
//    }
//
//    @Test
//    public void testGetAllOrdersAdmin() {
//        List list = ordersDao.getAllOrdersAdmin();
//        assertNotNull(list);
//    }
//
//}