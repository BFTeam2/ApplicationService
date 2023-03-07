package com.example.transactionmanagementdemo.service;

import com.example.transactionmanagementdemo.dao.ApplicationworkflowDao;
import com.example.transactionmanagementdemo.dao.EmployeeRepo;
import com.example.transactionmanagementdemo.dao.OrderProductDao;
import com.example.transactionmanagementdemo.domain.entity.Employee;
import com.example.transactionmanagementdemo.domain.request.PurchaseRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static org.mockito.Mockito.verify;

@WebMvcTest(EmployeeService.class)
public class EmployeeServiceTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean       // this annotation is provided by Spring Boot
    EmployeeService employeeService;

    @MockBean       // this annotation is provided by Spring Boot
    EmployeeRepo employeeRepo;
    @MockBean       // this annotation is provided by Spring Boot
    StorageService storageService;
    @MockBean       // this annotation is provided by Spring Boot
    ApplicationworkflowDao applicationworkflowDao;



    @BeforeAll
    public static void prepare() {
        //System.out.println("preparing resource");
    }

    @BeforeEach
    public void init() {
        //System.out.println("before each");
    }


    @Test
    public void testSaveOrUpdateEmployee_success() throws Exception {

//        EmployeeService service = new EmployeeService(storageService,employeeRepo,applicationworkflowDao);
        Employee employee=Employee.builder().firstName("huang").build();
        employeeService.saveOrUpdateEmployee(employee);
        verify(employeeService).saveOrUpdateEmployee(employee);
    }

    @Test
    public void testGetEmployeeByUserId_success() throws Exception {
        employeeService.getEmployeeByUserId(0L);
        verify(employeeService).getEmployeeByUserId(0L);
    }


    @Test
    public void testGetPersonalDocumentByEmployeeId_success() throws Exception {
        employeeService.getPersonalDocumentByEmployeeId("0");
        verify(employeeService).getPersonalDocumentByEmployeeId("0");
    }


    @Test
    public void testUpdateDocumentfile_success() throws Exception {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        employeeService.updateDocumentfile(file,"profile_picture","comment1","0");
        verify(employeeService).updateDocumentfile(file,"profile_picture","comment1","0");
    }

    @Test
    public void testDownloadFile_success() throws Exception {
        employeeService.downloadFile("0","1678008247848_dog.png");
        verify(employeeService).downloadFile("0","1678008247848_dog.png");
    }

}