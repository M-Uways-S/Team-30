package com.sheryians.major.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "product_name", referencedColumnName = "product_name")
    private String name;

    private double price;
    private int weight;
}