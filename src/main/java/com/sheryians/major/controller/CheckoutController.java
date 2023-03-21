package com.sheryians.major.controller;

import com.sheryians.major.dto.OrderDTO;
import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.CustomUserDetail;
import com.sheryians.major.model.Order;
import com.sheryians.major.model.Product;
import com.sheryians.major.repository.UserRepository;
import com.sheryians.major.service.CustomUserDetailService;
import com.sheryians.major.service.OrderService;
import com.sheryians.major.service.ProductService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckoutController {
    @Autowired
    OrderService orderService;
    UserRepository userDetails;

    @GetMapping (path="checkout")
    public String payNowGet(Model model){
        model.addAttribute("orderDTO", new OrderDTO());
        
        return "checkout";
    }

    @PostMapping (path="payNow")
    public String payNow(@ModelAttribute("orderDTO") OrderDTO orderDTO){
        //get order from cart
        for (Product product : GlobalData.cart){
            Order order = new Order();
            order.setId(orderDTO.getId());
            order.setPrice(product.getPrice());
            order.setProduct(product);
            order.setUser(userDetails.findUserByEmail("test1@gmail.com").get()); // CHANGE TO: order.setUser(orderDTO.getUser());
            order.setWeight(1); // CHANGE TO: order.setWeight(orderDTO.getWeight());

            //send order to orders
            orderService.addOrder(order);
        }

        //empty cart
        GlobalData.cart = new ArrayList<Product>();

        //return home
        return "index";
    }
}