package com.johnwaithaka.angel.controllers;

import com.johnwaithaka.angel.DTOs.AdminDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OtherPagesController {

    @RequestMapping("/add-content")
    public String addContent(Model model){
        AdminDto adminDto = new AdminDto();
        model.addAttribute("newAdmin", adminDto);
        return "add-content";
    }

    @RequestMapping("/fragments")
    public String getFrags(){
        return "fragments.html";
    }
}
