package com.sheryians.major.controller;


import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.CustomUserDetail;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.UserRepository;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.OrderService;
import com.sheryians.major.service.ProductService;
import org.apache.tomcat.jni.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserRepository userRepository;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
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

    @GetMapping({"/myOrders"})
    public String myOrders(){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object prcpl = authentication.getPrincipal();
        CustomUserDetail cud = CustomUserDetail.class.cast(prcpl);
        User user = userRepository.findUserByEmail(cud.getEmail())
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + cud.getEmail()));

        return "redirect:/myOrders/"+user.getId();
    }

    @GetMapping({"/myOrders/{id}"})
    public String myOrders(Model model, @PathVariable int id){
        model.addAttribute("orders", orderService.getAllOrdersByUserId(id));
        return "customerOrders";
    }


}
