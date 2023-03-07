package com.example.transactionmanagementdemo.domain.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "visaStatuses") // no longer use @Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VisaStatus {
    @Id
    private ObjectId id;

    private String visaType;
    private String activeFlag;

    private String startDate;
    private String endDate;
    private String lastModificationDate;

}
