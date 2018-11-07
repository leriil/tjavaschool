package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Address;
import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class AddressService {

    private static final Logger log = Logger.getLogger("Log");

    @Autowired
    AddressRepository addressRepository;

    @Transactional
    public Address userCurrentAddress(User user){
    if(user.getAddresses().iterator().hasNext()){
        return user.getAddresses().iterator().next();
    }else return null;
}

    public void save(Address address){
        this.addressRepository.save(address);
    }


    public void saveAddress(Address oldAddress, Address newAddress) {

    }
}




