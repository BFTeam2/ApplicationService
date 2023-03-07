package com.example.transactionmanagementdemo.domain.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personalDocuments") // no longer use @Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonalDocument {
    @Id
    private ObjectId id;

    private String path;
    private String title;

    private String comment;
    private String createDate;

}
