package com.example.transactionmanagementdemo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Builder
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
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // when persisting a question, hibernate should also persist all choices inside the Collection
    // telling Hibernate that information can be found in Choice class, under field "question"
    @ToString.Exclude   // prevent infinite loop when calling toString()
    @JsonIgnore
    private List<OrderProduct> orderProducts = new ArrayList<>();   //can be any Collection
}
