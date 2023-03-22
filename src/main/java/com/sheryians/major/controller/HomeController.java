package com.sheryians.major.controller;


import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.Product;
import com.sheryians.major.model.User;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;
import com.sheryians.major.service.ShopService;
import org.apache.tomcat.jni.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @Autowired
    ShopService shopService;

    @GetMapping({"/", "/home"})
    public String home(Model model, Authentication authentication) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            model.addAttribute("firstName", user.getFirstName());
        }
        return "index";
    }


    @GetMapping({"/contactUs"})
    public String contactus() {
        return "contactUs";
    }

    @GetMapping({"/aboutUs"})
    public String aboutus() {
        return "aboutUs";
    }

    @GetMapping({"/shop"})
    public String shop(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "shop";
    }

    @RequestMapping(path = {"/shop/search"})
    public String searchByCategory(Product product, Model model, String keyword) {
        if(keyword!=null) {
            model.addAttribute("products", shopService.getByKeyword(keyword));
        }else {
            model.addAttribute("products", shopService.getAllShops());}
        return "shop";
    }

    @GetMapping({"/shop/category/{id}"})
    public String shopByCategory(Model model, @PathVariable int id) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "shop";
    }

    @GetMapping({"/shop/viewproduct/{id}"})
    public String viewProduct(Model model, @PathVariable int id) {
        model.addAttribute("product", productService.getProductById(id).get());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "viewProduct";
    }


}
