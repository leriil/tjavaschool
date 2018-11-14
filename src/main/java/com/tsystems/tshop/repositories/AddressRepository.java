package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
//    public List<Address> findByUsers_Login(String login);

    List<Address> findByCountry(String country);

    @Query("select u from User u join fetch u.address where u.login=:login")
    List<Object[]> findAddressByUserLogin(@Param("login") String login);

//    @Query("select a from Address a JOIN a.users u where u.login=:login" )
//    public List<Address> queryOne(String login);
}