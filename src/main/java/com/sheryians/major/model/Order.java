package com.sheryians.major.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name= "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "ID")
    private Product product;

    private double price;
    private int stock;
    @Column(columnDefinition = "boolean default false")
    private boolean isShipped;
}