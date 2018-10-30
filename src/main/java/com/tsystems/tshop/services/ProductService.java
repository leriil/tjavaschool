package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.domain.ProductTop;
import com.tsystems.tshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductService {

    private static final java.util.logging.Logger log = Logger.getLogger("Log");
    @Autowired
    ProductRepository repository;


    public void save(Product product) {
        this.repository.save(product);
    }

    public List<Product> findProducts() {
        return this.repository.findAll();
    }

    public Product findOne(Long productId) throws RuntimeException {
        Optional<Product> product = this.repository.findById(productId);
        if (product.isPresent()) {
            return product.get();
        } else
        {log.log(Level.WARNING,"There is no product with id: "+productId.toString());
            throw new RuntimeException();}
    }

    @Transactional
    public List<ProductTop> getTopProducts(){

        return this.repository.getTopProducts();
    }

}
