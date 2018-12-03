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

    /**First checks if the user is already registered. If it's a new user,
     * it checks if the login and password entered by the user are already in use
     * and generates a specific error message in case they are. Otherwise,
     * if the user is already registered and has their unique id, the method checks
     * the email they entered. In case it doesn't match the email used by the user previously
     * and the email is already registered for another user, the value is rejected and an error
     * message is displayed.
     * @param target info posted by the current user for updating an accout
     * @param errors rejects value if the login or email are already in use
     */
    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        if(Objects.isNull(user.getUserId())){
            if (Objects.nonNull(userService.getUserByEmail(user.getEmail()))) {
                errors.rejectValue("email", "user.email", "The email you provided is already in use.");
            }
            if (Objects.nonNull(userService.getUser(user.getLogin()))) {
                errors.rejectValue("login", "user.login", "The username is already in use.");
            }
        }else{
            boolean sameEmail=userService.getUser().getEmail().equals(user.getEmail());
            User userWithSuchEmail = userService.getUserByEmail(user.getEmail());
            if((!sameEmail)&&(Objects.nonNull(userWithSuchEmail))){
                    errors.rejectValue("email", "user.email", "The email you provided is already in use.");
            }
        }

    }
}
