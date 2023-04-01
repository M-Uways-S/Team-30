package com.group30.major.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group30.major.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Long> {
    List<Product> findAllByCategory_Id(int id);
    List<Product> findByNameContainingIgnoreCase(String query);
}
