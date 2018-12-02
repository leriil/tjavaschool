package com.tsystems.tshop.validators;

import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

public class EmailAndLoginValidator implements Validator {

    private final UserService userService;

    @Autowired
    public EmailAndLoginValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(Objects.nonNull(userService.getUserByEmail(user.getEmail()))){
            errors.rejectValue("email","user.email","The email you provided is already in use.");
        }
        if(Objects.nonNull(userService.getUser(user.getLogin()))){
            errors.rejectValue("login","user.login","The username is already in use.");
        }


    }
}
