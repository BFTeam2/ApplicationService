package com.example.transactionmanagementdemo.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="Order_Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id", unique = true, nullable = false)
    private Integer order_product_id;

    @Column(name = "purchased_quantity")
    private int purchased_quantity;

    @Column(name = "execution_retail_price")
    private float execution_retail_price;

    @Column(name = "execution_wholesale_price")
    private float execution_wholesale_price;


    @ManyToOne(fetch = FetchType.LAZY)  // the Owner of the relationship
    @JoinColumn(name = "order_id") // <- name here is the exact name Hibernate can use when looking for fk in the "choice" table inside database
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)  // the Owner of the relationship
    @JoinColumn(name = "product_id") // <- name here is the exact name Hibernate can use when looking for fk in the "choice" table inside database
    private Product product;
}

