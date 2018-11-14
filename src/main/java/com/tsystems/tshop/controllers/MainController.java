package com.tsystems.tshop.controllers;

import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")

public class MainController {

//    private static final Logger log=Logger.getLogger("LOGGER");
    Logger LOGGER = LogManager.getLogger(MainController.class);


    UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }



    @RequestMapping(value="/register",method = RequestMethod.POST)
    public String register(@ModelAttribute User user){
        this.userService.saveAndAuthenticateNewUser(user);
        LOGGER.warn("new user has been registered"+user);
        return "redirect:/product/all";
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