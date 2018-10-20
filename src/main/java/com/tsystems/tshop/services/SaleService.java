package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Sale;
import com.tsystems.tshop.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;
@Service
public class SaleService {

    private static final java.util.logging.Logger log = Logger.getLogger("Log");

    @Autowired
    SaleRepository saleRepository;

    public void saveSale(Sale sale){
        this.saleRepository.save(sale);
        log.log(Level.WARNING,"sale saved"+sale.toString());
    }

}
