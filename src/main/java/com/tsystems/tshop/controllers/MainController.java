package com.tsystems.tshop.controllers;

import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.services.UserService;
import com.tsystems.tshop.validators.PasswordValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")

public class MainController {


    private static final Logger LOGGER = LogManager.getLogger(MainController.class);


    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user,
                           Errors errors){

        if(!errors.hasErrors()) {

            this.userService.saveAndAuthenticateNewUser(user);
            LOGGER.warn("new user has been registered" + user);
            return "redirect:/product/all";

        }

        return "register";
    }


    @GetMapping(value = "/register")
    public String goRegister(Model model, @ModelAttribute User user){

        model.addAttribute("user",user);
        return "register";
    }


    @GetMapping()
    public String start(){
        return "redirect:/product/all";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(new PasswordValidator());
    }

}