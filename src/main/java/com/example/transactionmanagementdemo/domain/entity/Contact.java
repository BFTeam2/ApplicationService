package com.example.transactionmanagementdemo.domain.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contacts") // no longer use @Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    @Id
    private ObjectId id;

    private String firstName;
    private String lastName;

    private String cellPhone;
    private String alternatePhone;

    private String Email;

    private String relationship;
    private String type;


}
