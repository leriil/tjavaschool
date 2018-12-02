package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    User findByLogin(String login);

    User findByEmail(String email);

}