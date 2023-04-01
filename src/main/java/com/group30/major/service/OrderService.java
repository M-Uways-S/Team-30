package com.group30.major.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group30.major.model.Order;
import com.group30.major.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }
    public void addOrder(Order order) {
        orderRepository.save(order);
    }
    public void removeOrderById(Long id) {
        orderRepository.deleteById(id);
    }
    public Optional<Order> getOrderById(long id) {
        return orderRepository.findById(id);
    }
    public List<Order> getAllOrdersByUserId(Integer id) {
        return orderRepository.findAllByUser_Id(id);
    }
}