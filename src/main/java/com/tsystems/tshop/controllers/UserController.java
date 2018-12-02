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

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes({"user"})
public class UserController {

    private final UserService userService;
    Logger LOGGER = LogManager.getLogger(UserController.class);


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    User getUser() {
        return new User();
    }

    @GetMapping("/account")
    public String getAccount(Model model,
                             @ModelAttribute("user") User user) {

        model.addAttribute("user", userService.getUser());

        return "account";
    }

    @PostMapping("/account/update")
    public String updateAccount(Model model,
                                @Valid @ModelAttribute("user") User user,
                                Errors errors) {
        if (!errors.hasErrors()) {
            user = userService.updateUserInfo(user);
            model.addAttribute("user", user);
            return "redirect:/user/account?updated=true";
        }
        return "account";
    }

    @ResponseBody
    @GetMapping("/find/top")
    public List<UserTop> getTopUsers(
            @RequestParam(value = "order", required = false) String order
    ) {
        return userService.getTopUsers(order);
    }

    @GetMapping(value = "/{userId}")
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
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new PasswordValidator(), new EmailAndLoginValidator(userService));
    }
}
