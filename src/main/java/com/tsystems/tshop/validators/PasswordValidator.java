package com.tsystems.tshop.validators;

import com.tsystems.tshop.domain.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {

        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//                (?=.*[0-9])       # a digit must occur at least once
//                (?=.*[a-z])       # a lower case letter must occur at least once
//                (?=.*[A-Z])       # an upper case letter must occur at least once
//                (?=\S+$)          # no whitespace allowed in the entire string
//                .{8,}             # anything, at least eight places though
//        $                 # end-of-string
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{4,}$";
        User user = (User) target;
        if (!user.getPassword().matches(pattern)) {
            errors.rejectValue("password", "user.password", "the password is too weak");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "user.confirm.password", "password confirmation failed");
        }
    }
}
