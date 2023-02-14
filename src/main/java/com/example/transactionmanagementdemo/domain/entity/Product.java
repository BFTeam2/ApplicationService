package com.example.transactionmanagementdemo.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true, nullable = false)
    private Integer product_id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "retail_price")
    private float retail_price;

    @Column(name = "wholesale_price")
    private float wholesale_price;

    @Column(name = "stock_quantity")
    private Integer stock_quantity;

    @ManyToMany(mappedBy = "watchlistProducts", cascade = CascadeType.ALL)
    @ToString.Exclude   // to prevent infinite loop
    private List<User> users = new ArrayList<>();
}
