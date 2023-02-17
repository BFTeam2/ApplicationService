package com.example.transactionmanagementdemo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    int user_id;
    @Column(name = "username")
    private String username;


    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "permission")
    private String permission;


    @ManyToMany(cascade = CascadeType.ALL)      // owner of relationship
    @JoinTable(name = "Product_WatchList",
            joinColumns = {@JoinColumn(name = "user_id")},           //joinColumn specify current class's fk
            inverseJoinColumns = {@JoinColumn(name = "product_id")})     //inverseJoinColumn that of referenced class's fk
    List<Product> watchlistProducts = new ArrayList<>();
}
