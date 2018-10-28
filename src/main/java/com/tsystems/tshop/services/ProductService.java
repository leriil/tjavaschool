package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    EntityManager em;

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
    public List<Product> getTopProducts(){

        javax.persistence.Query query=em.createNativeQuery("select product_id, category, in_stock, name, price, volume, weight " +
                "from(select * from product left join " +
                "(select product.product_id as pid, count(sale_product.product_id) as top " +
                "from product join sale_product " +
                "on product.product_id=sale_product.product_id " +
                "group by pid) as s on product.product_id=s.pid) as p order by p.top desc");

        List<Product> topProducts=query.setMaxResults(2).getResultList();

        return topProducts;
    }

}
