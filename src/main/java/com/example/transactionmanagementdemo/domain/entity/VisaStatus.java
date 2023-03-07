package com.example.transactionmanagementdemo.domain.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresss") // no longer use @Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    @Id
    private ObjectId id;

    private String addressLine1;
    private String addressLine2;

    private String city;
    private String state;
    private String zipCode;

}
