package com.johnwaithaka.angel.controllers;

import com.johnwaithaka.angel.DTOs.AngelDTO;
import com.johnwaithaka.angel.DTOs.RegistrationResponse;
import com.johnwaithaka.angel.entities.Angel;
import com.johnwaithaka.angel.exception.UsernameExistsException;
import com.johnwaithaka.angel.services.AngelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthAndHomePageController {

    @Autowired
    AngelService angelService;

    Logger log = LoggerFactory.getLogger(AuthAndHomePageController.class);

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
    public String loginSuccessful(
            HttpServletRequest request,
            Authentication authentication,
            RedirectAttributes redirectAttributes
    ){
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        if(request.isUserInRole("ADMIN")){
            return "redirect:/home-admin/";
        } else{
            //fetch user details
            Optional<Angel> o = angelService.findByUsername(
                    authentication.getName()
            );

            redirectAttributes.addFlashAttribute("Angel", o.get());
            return "redirect:/user/home/";
        }
    }

    @GetMapping(value = "/user/registration")
    public String registration(Model model){
        model.addAttribute("user", new AngelDTO());
        return "angel-registration";
    }

    @RequestMapping(value = "/user/registration1")
    public String register(
            @ModelAttribute("user") @Valid AngelDTO angelDTO,
            RedirectAttributes redirectAttributes,
            BindingResult result,
            HttpServletRequest request
    ){
        Angel angel = regUser(angelDTO);
//        RegistrationResponse response = new RegistrationResponse();

        if(angel == null){
            result.rejectValue("username", "error.user", "Username exists");
            return "angel-registration";
        }
        if(result.hasErrors()){
            return "angel-registration";
        }

        try {
            request.login(angel.getUsername(), angelDTO.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("Angel", angel);
        return "redirect:/user/home";
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

    @RequestMapping("/user/home")
    public String userHome(){
        return "home-user";
    }

    @RequestMapping("/temp-suc-reg")
    @ResponseBody
    public String sucReg(){
        return "Suc reg";
    }

}
