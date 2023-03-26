package com.sheryians.major.controller;

import com.sheryians.major.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.sheryians.major.model.Userdata;

@Controller
public class IndexController {
    @Autowired
    private UserRepo Userrepo;
    // @GetMapping("/")
    // public String index() {

    // return "contactUs";
    // }

    @PostMapping("/REGISTER")
    public String userRegistration(@ModelAttribute Userdata userdata, Model model) {
        System.out.println(userdata.toString());
        // validate
        System.out.println(userdata.getFirstname());
        System.out.println(userdata.getSecondname());
        System.out.println(userdata.getPhonenumber());
        System.out.println(userdata.getEmail());
        System.out.println(userdata.getCategory());
        System.out.println(userdata.getTexta());
        Userdata user_inserted = Userrepo.save(userdata);
        return "contactUs";
    }

    @GetMapping("admin/CustomerFeedback")
    public String showUserList(Model model) {
        Iterable<Userdata> userdata = Userrepo.findAll();
        model.addAttribute("userdata", userdata);
        return "CustomerFeedback";
    }

}