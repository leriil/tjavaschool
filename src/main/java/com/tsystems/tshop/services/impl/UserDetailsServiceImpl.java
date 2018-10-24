package com.tsystems.tshop.services.impl;

import com.tsystems.tshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    public UserService userService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return userService.getUser(s);

    }
}
