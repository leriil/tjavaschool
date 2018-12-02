package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getByName(String name);
}
