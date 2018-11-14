package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Role;
import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    UserRepository userRepository;

    RoleService roleService;

    AddressService addressService;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleService roleService,
                       AddressService addressService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.addressService=addressService;
    }

    public void saveAndAuthenticateNewUser(User user){
        Set<Role> roles=new HashSet<>();
        roles.add(this.roleService.getRoleByName("CLIENT"));
        user.setRoles(roles);
//        Address a = user.getAddress();
        user.setAddress(null);
//        this.addressService.save(a);
        this.userRepository.save(user);
        //build an authentication object for the user
        Authentication authentication=new UsernamePasswordAuthenticationToken(user, user.getPassword(),user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public void updateUserInfo(User user){
        this.addressService.save(user.getAddress());
        this.userRepository.save(user);
    }

    public User getUser(){
        String login=SecurityContextHolder.getContext().getAuthentication().getName();
        return this.userRepository.findByLogin(login);
    }
public User getUser(String login){
        return this.userRepository.findByLogin(login);
}


}
