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

import javassist.expr.NewArray;

import java.security.Principal;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

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

        List<Order> orders = new ArrayList<Order>();

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

            order.setStock(1); // CHANGE TO: order.setStock(orderDTO.getStock());

            // Shipping status
            order.setShipped(false);

            // Set date
            order.setDate(java.time.LocalDate.now().toString());

            //send order to orders
            orders.add(order);
        }

        Order prevOrder = orders.get(0);
        Product prevOrderProduct = prevOrder.getProduct();
        orders.remove(0);

        for (Order order: orders){
            Product product = order.getProduct();

            if (product.getId() != prevOrderProduct.getId()){
                // Remove order from stock
                product.setStock(product.getStock()-prevOrder.getStock());
                productService.addProduct(product);
                // Add to orders table
                orderService.addOrder(prevOrder);
                // Change prevOrder
                prevOrder = order;
                prevOrderProduct = product;
            } else {
                // Add to quantity of order
                prevOrder.setStock(prevOrder.getStock()+1);
                prevOrder.setPrice(prevOrder.getPrice()+prevOrderProduct.getPrice());
            }
        }

        // Repeat of the if statement for final product
        prevOrderProduct.setStock(prevOrderProduct.getStock()-prevOrder.getStock());
        productService.addProduct(prevOrderProduct);
        orderService.addOrder(prevOrder);

        //empty cart
        GlobalData.cart = new ArrayList<Product>();

        //return home
        return "index";
    }
}