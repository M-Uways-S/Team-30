package com.group30.major.dto;

import com.group30.major.model.Product;
import com.group30.major.model.User;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private User user;
    private Product product;
    private double price;
    private int stock;
    private boolean isShipped;
    private String date;
}