package com.johnwaithaka.angel.payroll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(){
        System.out.println("lalaland");
        return "login";
    }

    @RequestMapping(value = "/fail_login")
    @ResponseBody
    public String fail_login(){
        return "Login failed";
    }
}
