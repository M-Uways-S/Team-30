package com.sheryians.major.controller;

import com.sheryians.major.dto.OrderDTO;
import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.Order;
import com.sheryians.major.model.Product;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.UserRepository;
import com.sheryians.major.service.CustomUserDetailService;
import com.sheryians.major.service.OrderService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Transactional
@Controller
public class CheckoutController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomUserDetailService userDetails;

    @GetMapping (path="checkout")
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

            User user = userRepository.findUserByEmail(authentication.getPrincipal().toString())
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + authentication.getPrincipal().toString()));

            order.setUser(user);

            /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                order.setUser((User) authentication.getPrincipal());
                }*/

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