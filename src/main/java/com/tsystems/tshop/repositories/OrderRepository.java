package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

    Set<Order>findAllByUser_Login(String login);

    List<Order> findAllByOrderDateBetween(LocalDate first, LocalDate last);


}
