package com.tsystems.tshop.services.impl;

import com.tsystems.tshop.domain.Role;
import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    public UserService userService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // с помощью нашего сервиса UserService получаем User
        User user= userService.getUser(s);
        // указываем роли для этого пользователя
//        Set<GrantedAuthority> roles = new HashSet();
//        roles.add(new SimpleGrantedAuthority(UserRole.USER.name()));

        List<Role> roles=user.getRoles();
        List<String>priveleges=new ArrayList<>();
        for (Role role:roles) {
            priveleges.add(role.getName());
            System.out.println(priveleges);
        }

        // на основании полученных данных формируем объект UserDetails
        // который позволит проверить введенный пользователем логин и пароль
        // и уже потом аутентифицировать пользователя

                return new org.springframework.security.core.userdetails.User(user.getLogin(),
                        user.getPassword(),AuthorityUtils.createAuthorityList(String.valueOf(priveleges)));
    }
}
