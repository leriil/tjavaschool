package com.tsystems.tshop.controllers;

import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.domain.UserTop;
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
import java.util.List;

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
    public String updateAccount(@Valid @ModelAttribute("user") User user,
                                Errors errors){
        if(!errors.hasErrors()){
        this.userService.updateUserInfo(user);
        return "redirect:/product/all";}
        return "account";
    }

    @ResponseBody
    @RequestMapping("/find/top")
    public List<UserTop> getTopUsers(
            @RequestParam(value = "order", required = false) String order
    ){
        return userService.getTopUsers(order);
    }

    @RequestMapping(value = "/{userId}")
    public String findProduct(Model model,
                              @PathVariable Long userId) {
        try {
            model.addAttribute("user", userService.getUserById(userId));
        } catch (RuntimeException e) {
            return "user_not_found";
        }
        return "user";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder){
binder.addValidators(new PasswordValidator());
    }
}
