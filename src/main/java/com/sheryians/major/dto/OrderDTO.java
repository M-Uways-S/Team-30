package com.sheryians.major.dto;

import com.sheryians.major.model.Product;
import com.sheryians.major.model.User;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private User user;
    private Product product;
    private double price;
    private int stock;
    private boolean isShipped;
}