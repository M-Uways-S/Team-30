package com.group30.major.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group30.major.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser_Id(Integer id);
}