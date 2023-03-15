package com.sheryians.major.service;

import com.sheryians.major.model.Order;
import com.sheryians.major.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}