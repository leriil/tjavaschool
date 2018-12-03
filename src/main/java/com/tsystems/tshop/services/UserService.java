package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Role;
import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.domain.UserTop;
import com.tsystems.tshop.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
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

    /**Creates a new user with role ClIENT, no address, and an encoded password,
     * then authenticates that user.
     * @param user is saved and authenticated
     */
    public void saveAndAuthenticateNewUser(User user) {

        Set<Role> roles = new HashSet<>();
        roles.add(this.roleService.getRoleByName("CLIENT"));
        user.setRoles(roles);
        user.setAddress(null);
        this.encodeAndSetPassword(user);
        userRepository.save(user);
        //build an authentication object for the user
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * @param user is saved to the database
     */
    public void saveUser(User user) {

        userRepository.save(user);
    }

    /**
     * updates user info and password
     *
     * @param user is updated
     * @return the updated user
     */
    public User updateUserInfo(User user) {

        addressService.save(user.getAddress());
        this.encodeAndSetPassword(user);
        userRepository.save(user);
        return this.getUser();
    }

    /**
     * gets the user from the database by their login, obtain from the security context
     *
     * @return current user
     */
    public User getUser() {

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByLogin(login);
    }

    /**
     * returns a user by the login, passed as an argument
     *
     * @param login user's login
     * @return user with a specified login
     */
    public User getUser(String login) {

        return userRepository.findByLogin(login);
    }


    /**
     * @param order an order in which the users should be returned (asc or desc)
     * @return a list of users ordered by the amount of money they spent
     */
    public List<UserTop> getTopUsers(String order) {

        if (!order.contains("alt")) {
            return userRepository.getTopUsersAsc();
        } else return userRepository.getTopUsersDesc();
    }

    /**
     * @param userId used to find a user in the database
     * @return a user with the specified id
     * @throws NoSuchElementException if the user is not present in the database
     */
    public User getUserById(Long userId) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else throw new NoSuchElementException();
    }

    /**
     * @param email used to find the user
     * @return a user with the specified email
     */
    public User getUserByEmail(String email) {

        User user = userRepository.findByEmail(email);
        LOGGER.info("The user with email {} is: {}", email, user);
        return user;
    }

    /** Encodes the password entered by the user and assigns it to password
     * and confirmPassword fields.
     * @param user for whom the password is set
     */
    private void encodeAndSetPassword(User user) {

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setConfirmPassword(password);
    }
}
