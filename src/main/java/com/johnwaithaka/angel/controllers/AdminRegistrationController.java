package com.johnwaithaka.angel.controllers;

import com.johnwaithaka.angel.DTOs.AdminDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Controller
public class AdminRegistrationController {

    @RequestMapping(value = "/register-admin")
    @ResponseStatus(value = HttpStatus.OK)
    public void registerAdmin(@ModelAttribute("newAdmin") AdminDto adminDto){
        String tempPass = RandomStringUtils.random(8, true, true);
        adminDto.setPassword(tempPass);

        System.out.println("Username: " + adminDto.getEmail() + " Password: " + adminDto.getPassword());
    }
}
