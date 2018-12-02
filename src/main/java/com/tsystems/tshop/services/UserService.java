package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Role;
import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.domain.UserTop;
import com.tsystems.tshop.repositories.UserRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {


    private final UserRepository userRepository;

    private final RoleService roleService;

    private final AddressService addressService;

    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository,
                       RoleService roleService,
                       AddressService addressService,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveAndAuthenticateNewUser(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(this.roleService.getRoleByName("CLIENT"));
        user.setRoles(roles);
        user.setAddress(null);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setConfirmPassword(password);
        this.userRepository.save(user);
        //build an authentication object for the user
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User updateUserInfo(User user) {
        this.addressService.save(user.getAddress());
        this.userRepository.save(user);
        return this.getUser();
    }

    public User getUser() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.userRepository.findByLogin(login);
    }

    public User getUser(String login) {
        return this.userRepository.findByLogin(login);
    }


    public List<UserTop> getTopUsers(String order) {
        if (!order.contains("alt")) {
            return userRepository.getTopUsersAsc();
        } else return userRepository.getTopUsersDesc();
    }

    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else throw new RuntimeException();
    }

    public User getUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user;
    }
}
