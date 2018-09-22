package com.johnwaithaka.angel.controllers;

import com.johnwaithaka.angel.DTOs.AdminDto;
import com.johnwaithaka.angel.models.MenuItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OtherPagesController {

    @RequestMapping("/add-content")
    public String addContent(Model model){
        AdminDto adminDto = new AdminDto();
        model.addAttribute("newAdmin", adminDto);

        List<MenuItem>  menuItems1 = new ArrayList<>();
        menuItems1.add(new MenuItem("Level 1", "levelconfig/0"));
        menuItems1.add(new MenuItem("Level 2", "levelconfig/0"));
        model.addAttribute("menuItems", new MenuItem[]{new MenuItem("Levels", "levelconfig/0", menuItems1)});

        return "add-content";
    }

    @RequestMapping("/fragments")
    public String getFrags(){
        return "fragments.html";
    }
}
