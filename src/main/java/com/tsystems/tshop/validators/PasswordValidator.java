package com.tsystems.tshop.validators;

import com.tsystems.tshop.domain.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","user.password","this field should not be empty or contain whitespaces");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"confirmPassword","user.confirm.password", "this field should not be empty or contain whitespaces");
//TODO: add a regex for the password
//        ^                 # start-of-string
//                (?=.*[0-9])       # a digit must occur at least once
//                (?=.*[a-z])       # a lower case letter must occur at least once
//                (?=.*[A-Z])       # an upper case letter must occur at least once
//                (?=.*[@#$%^&+=])  # a special character must occur at least once
//        (?=\S+$)          # no whitespace allowed in the entire string
//                .{8,}             # anything, at least eight places though
//        $                 # end-of-string
        String pattern = "^(?=.*[0-9])(?=.*[0-9])(?=.*[A-Z]).{8,}$";
//TODO: Patternm Matcher here
        User user =(User) target;
        if(!user.getPassword().matches(pattern)){
            errors.rejectValue("password","user.password","the password contains illegal characters or is too weak");
        }
        if(!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("password","user.password", "password confirmation failed");
        }
    }
}
