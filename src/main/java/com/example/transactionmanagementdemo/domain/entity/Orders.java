package com.example.transactionmanagementdemo.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false)
    private Integer order_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "order_status")
    private String order_status;

    @Column(name = "date_places")
    private Timestamp date_places;
}
