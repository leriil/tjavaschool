package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Address;
import com.tsystems.tshop.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AddressService {

    private static final Logger log = Logger.getLogger("Log");

    @Autowired
    AddressRepository addressRepository;

    public void saveAddress(Address address){
        List<Address>addresses=new ArrayList<>();
        addresses=this.addressRepository.findAll();
        for (int i = 0; i <addresses.size() ; i++) {
            if (addresses.get(i).equals(address)){
                log.log(Level.WARNING,"this address: "+address.toString()+" is already in the database");
            }else {this.addressRepository.save(address);}
        }
    }

//    public List<Address> getUserAddresses(String login){
//        return this.addressRepository.findByUsers_Login(login);
//    }


}
