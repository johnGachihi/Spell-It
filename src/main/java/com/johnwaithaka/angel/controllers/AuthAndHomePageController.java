package com.johnwaithaka.angel.controllers;

import com.johnwaithaka.angel.DTOs.AngelDTO;
import com.johnwaithaka.angel.DTOs.RegistrationResponse;
import com.johnwaithaka.angel.entities.Angel;
import com.johnwaithaka.angel.exception.UsernameExistsException;
import com.johnwaithaka.angel.services.AngelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthAndHomePageController {

    @Autowired
    AngelService angelService;

    /*Should be removed*/
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    /*failLogin url*/
    @RequestMapping(value = "/fail_login")
    @ResponseBody
    public String fail_login(){
        return "Login failed";
    }

    @RequestMapping(value = "/login-success")
    public String loginSuccessful(HttpServletRequest request){
        if(request.isUserInRole("ADMIN")){
            return "redirect:/home-admin/";
        } else
            return "redirect:/user/registration";
    }

    @GetMapping(value = "/user/registration")
    public String registration(Model model){
        model.addAttribute("user", new AngelDTO());
        return "angel-registration";
    }

    @RequestMapping(value = "/user/registration")
    @ResponseBody
    public RegistrationResponse register(
            @ModelAttribute("user") @Valid AngelDTO angelDTO,
            BindingResult result
    ){
        Angel angel = regUser(angelDTO);
        RegistrationResponse response = new RegistrationResponse();

        if(angel == null){
            response.setError(true);
            response.setErrorMessage("Username already exists");
        } else {
            response.setError(false);
            response.setAngel(angel);
        }
        return response;

    }

    private Angel regUser(AngelDTO angelDTO){
        Angel angel = null;
        try {
            angel = angelService.registerUser(angelDTO);
        } catch (UsernameExistsException e) {
            e.printStackTrace();
            return null;
        }
        return angel;
    }

    @RequestMapping("/temp-suc-reg")
    @ResponseBody
    public String sucReg(){
        return "Suc reg";
    }

}
