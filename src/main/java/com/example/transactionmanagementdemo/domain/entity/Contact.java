package com.example.transactionmanagementdemo.domain.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "employees") // no longer use @Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    @Id
    private ObjectId id;

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




//    private List<Award> awards = new ArrayList<>();
}
