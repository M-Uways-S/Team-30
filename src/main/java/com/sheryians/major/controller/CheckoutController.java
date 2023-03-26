package com.sheryians.major.controller;

import com.sheryians.major.dto.OrderDTO;
import com.sheryians.major.dto.ProductDTO;
import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.CustomUserDetail;
import com.sheryians.major.model.Order;
import com.sheryians.major.model.Product;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.UserRepository;
import com.sheryians.major.service.CustomUserDetailService;
import com.sheryians.major.service.OrderService;
import com.sheryians.major.service.ProductService;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Transactional
public class CheckoutController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomUserDetailService userDetails;
    @Autowired
    ProductService productService;

    @GetMapping (path="checkout")
    @Transactional
    public String payNowGet(Model model){
        model.addAttribute("orderDTO", new OrderDTO());
        
        return "checkout";
    }


    @PostMapping (path="payNow")
    @Transactional
    public String payNow(@ModelAttribute("orderDTO") OrderDTO orderDTO){
        //get order from cart
        for (Product product : GlobalData.cart){
            Order order = new Order();
            order.setId(orderDTO.getId());
            order.setPrice(product.getPrice());
            order.setProduct(product);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            Object prcpl = authentication.getPrincipal();
            CustomUserDetail cud = CustomUserDetail.class.cast(prcpl);
            System.out.println(cud.getEmail());

            User user = userRepository.findUserByEmail(cud.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + cud.getEmail()));

            order.setUser(user);

            order.setWeight(1); // CHANGE TO: order.setWeight(orderDTO.getWeight());

            // Remove order from stock
            product.setWeight(product.getWeight()-order.getWeight());
            productService.addProduct(product);

            // Shipping status
            order.setShipped(false);

            //send order to orders
            orderService.addOrder(order);
        }

        //empty cart
        GlobalData.cart = new ArrayList<Product>();

        //return home
        return "index";
    }
}