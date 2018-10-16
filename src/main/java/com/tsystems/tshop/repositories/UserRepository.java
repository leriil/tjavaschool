package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByLogin(String login);

}
