package com.williams.identityverification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.williams.identityverification.model.model.*;

@Controller
public class CustomersController {

    @GetMapping("/customer")
    public String getCustomer(){
        return "customer";
    }

    @ModelAttribute("customer")
    public Customer customer(){
        return new Customer("Muyiwa",30L,"India");
    }
}
