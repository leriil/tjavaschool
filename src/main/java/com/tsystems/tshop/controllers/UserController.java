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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/user")
@SessionAttributes({"user"})
public class UserController {

//    private static final Logger log=Logger.getLogger("LOGGER");
    Logger LOGGER = LogManager.getLogger(UserController.class);

    private UserService userService;


    @Autowired
    public UserController(UserService userService
                          ) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    User getUser(){return new User();}

    @RequestMapping("/account")
    public String getAccount(Model model,
                             @ModelAttribute("user") User user){

        model.addAttribute("user",this.userService.getUser() );

        return "account";
    }

    @RequestMapping(value = "/account/update", method = RequestMethod.POST)
    public String updateAccount(@ModelAttribute("user") User user){
        this.userService.updateUserInfo(user);
        return "redirect:/product/all";
    }

}
