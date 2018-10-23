package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;


    public User getUser(){
        String login=SecurityContextHolder.getContext().getAuthentication().getName();
        return this.userRepository.findByLogin(login);
    }
public User getUser(String login){
        return this.userRepository.findByLogin(login);
}


}
