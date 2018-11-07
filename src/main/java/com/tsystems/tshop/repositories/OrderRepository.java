package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Set<Order>findAllByUser_Login(String login);


}
