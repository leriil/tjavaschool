package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Address;
import com.tsystems.tshop.repositories.AddressRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private static final Logger LOGGER = LogManager.getLogger(AddressService.class);

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {

        this.addressRepository = addressRepository;
    }

    public void save(Address address) {

        this.addressRepository.save(address);
        LOGGER.info("Address {} has been saved", address);
    }

}




