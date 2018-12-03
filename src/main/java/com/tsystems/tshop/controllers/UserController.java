package com.tsystems.tshop.controllers;

import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.domain.UserTop;
import com.tsystems.tshop.services.UserService;
import com.tsystems.tshop.validators.EmailAndLoginValidator;
import com.tsystems.tshop.validators.PasswordValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/user")
@SessionAttributes({"user"})
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @ModelAttribute("user")
    User getUser() {

        return new User();
    }

    @GetMapping("/account")
    public String getAccount(Model model
    ) {

        model.addAttribute("user", userService.getUser());

        return "account";
    }

    @PostMapping("/account/update")
    public String updateAccount(Model model,
                                @Valid @ModelAttribute("user") User user,
                                Errors errors,
                                SessionStatus status) {

        if (!errors.hasErrors()) {
            user = userService.updateUserInfo(user);
            LOGGER.info("Updated user: {}", user);
            model.addAttribute("user", user);
            status.setComplete();
            return "redirect:/user/account?updated=true";
        }
        return "account";
    }

    @ResponseBody
    @GetMapping("/find/top")
    public List<UserTop> getTopUsers(
            @RequestParam(value = "order", required = false) String order) {

        return userService.getTopUsers(order);
    }

    @GetMapping(value = "/{userId}")
    public String findUser(Model model,
                           @PathVariable Long userId) {

        try {
            model.addAttribute("user", userService.getUserById(userId));
        } catch (NoSuchElementException e) {
            return "user_not_found";
        }
        return "user";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.addValidators(new PasswordValidator(), new EmailAndLoginValidator(userService));
    }
}
