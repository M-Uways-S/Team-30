package com.sheryians.major.controller;

import com.sheryians.major.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sheryians.major.service.ShopService;
@Controller
public class ShopController {
    @Autowired
    private ShopService service;
    @RequestMapping(path = {"/search"})
    public String home(Product product, Model model, String keyword) {
        if(keyword!=null) {
            model.addAttribute("products", service.getByKeyword(keyword));
        }else {
            model.addAttribute("products", service.getAllShops());}
        return "index2";
    }
}