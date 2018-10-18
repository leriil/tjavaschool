package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Address;
import com.tsystems.tshop.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class AddressService {

    private static final Logger log = Logger.getLogger("Log");

    @Autowired
    AddressRepository addressRepository;

    public void saveAddress(Address address){




        this.addressRepository.save(address);
    }

    public List<Address> getUserAddresses(String login){
        return this.addressRepository.findByUsers_Login(login);
    }


}
