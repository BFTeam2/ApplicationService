package com.example.transactionmanagementdemo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name="Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
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

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // when persisting a question, hibernate should also persist all choices inside the Collection
    // telling Hibernate that information can be found in Choice class, under field "question"
    @ToString.Exclude   // prevent infinite loop when calling toString()
    @JsonIgnore
    private List<OrderProduct> orderProducts = new ArrayList<>();   //can be any Collection
}
