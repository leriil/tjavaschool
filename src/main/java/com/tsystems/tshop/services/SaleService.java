package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Sale;
import com.tsystems.tshop.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
@Service
public class SaleService {

    private static final java.util.logging.Logger log = Logger.getLogger("Log");

    @Autowired
    SaleRepository saleRepository;

    public void saveSale(Sale sale){
        this.saleRepository.save(sale);
        log.log(Level.WARNING,"we have saved::"+sale.toString());
    }
@Transactional
    public Set<Sale>findUserOrders(String login){

        return this.saleRepository.findAllByUser_Login(login);

    }

    public Sale findSale(Long id){
        Optional<Sale> sale = this.saleRepository.findById(id);
        if (sale.isPresent()) {
            return sale.get();
        } else
        {log.log(Level.WARNING,"There is no product with id: "+id.toString());
            throw new RuntimeException();}
    }
    }


