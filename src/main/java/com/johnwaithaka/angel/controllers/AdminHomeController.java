package com.johnwaithaka.angel.controllers;

import com.johnwaithaka.angel.services.LevelsService;
import org.springframework.beans.factory.annotation.Autowired;
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
