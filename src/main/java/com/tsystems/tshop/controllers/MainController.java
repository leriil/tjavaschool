package com.tsystems.tshop.controllers;

import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

@Controller
@RequestMapping("/")

public class MainController {

    private static final Logger log=Logger.getLogger("Log");

    @Autowired
    UserService userService;


    @RequestMapping(value="/register",method = RequestMethod.POST)
    public String register(@ModelAttribute User user){
        this.userService.saveAndAuthenticateNewUser(user);
        return "register";
    }


    @RequestMapping(value="/register",method = RequestMethod.GET)
    public String goRegister(){
        return "register";
    }
    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model){
        return "redirect:/product/all";
    }

}