package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> findByCountry(String country);

    //    @Query("select a from Address a JOIN a.users u where u.login=:login" )
//    public List<Address> queryOne(String login);
}