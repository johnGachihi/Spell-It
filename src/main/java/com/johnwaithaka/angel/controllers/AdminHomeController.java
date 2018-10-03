package com.johnwaithaka.angel.controllers;

import com.johnwaithaka.angel.services.AdminHomeService;
import com.johnwaithaka.angel.services.LevelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class AdminHomeController {

    @Autowired
    AdminHomeService adminHomeService;

    @RequestMapping(value = "/home-admin")
    public String homeAdmin(Model model){
        model.addAttribute("pageTitle", "Home-Admin");

        return "home-admin";
    }

    @RequestMapping(value = "/num-of-users")
    @ResponseBody
    public Long getNumberOfUsers(){
        return adminHomeService.getNumberOfRegisteredUsers();
    }
}
