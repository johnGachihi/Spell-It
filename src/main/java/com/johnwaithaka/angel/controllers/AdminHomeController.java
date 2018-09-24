package com.johnwaithaka.angel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminHomeController {

    @RequestMapping(value = "/home-admin")
    public String homeAdmin(Model model){
        model.addAttribute("pageTitle", "Home-Admin");
        return "home-admin";
    }
}
