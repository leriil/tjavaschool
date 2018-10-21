package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    public Set<Sale>findAllByUser_Login(String login);
}
