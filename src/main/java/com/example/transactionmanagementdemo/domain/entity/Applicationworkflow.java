package com.example.transactionmanagementdemo.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="applicationworkflow")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Applicationworkflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "EmployeeID")
    private String employeeID;

    @Column(name = "CreateDate")
    private Timestamp createDate;

    @Column(name = "LastModificationDate")
    private Timestamp lastModificationDate;



    @Column(name = "Status")
    private String status;

    @Column(name = "Comment")
    private String comment;
}

