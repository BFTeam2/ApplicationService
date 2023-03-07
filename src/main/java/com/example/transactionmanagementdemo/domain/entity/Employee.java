package com.example.transactionmanagementdemo.domain.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Document(collection = "employees") // no longer use @Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Employee {
    @Id
    private String id;

    private Long userId;

    private String firstName;
    private String lastName;

    private String middleName;
    private String preferedName;

    private String email;
    private String cellPhone;
    private String alternatePhone;

    private String gender;

    private String SSN;
    private String DOB;

    private String startDate;
    private String endDate;

    private String driverLicense;
    private String driverLicenseExpiration;

    private String houseID;

    private List<Contact> contacts = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private Map<String, PersonalDocument> personalDocuments = new HashMap<>();


    private VisaStatus visaStatuses = new VisaStatus();
}
