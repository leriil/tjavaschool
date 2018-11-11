package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.domain.ProductTop;
import com.tsystems.tshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductService {

    private static final java.util.logging.Logger log = Logger.getLogger("LOGGER");

    ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getSortedProducts(String option, String order){

        if(option.equals("price")&&(order.contains("alt"))){
            return this.repository.findAll(new Sort(Sort.Direction.ASC,"price"));
        }

        if(option.equals("price")&&!(order.contains("alt"))){
            return this.repository.findAll(new Sort(Sort.Direction.DESC,"price"));
        }
        if(option.equals("popularity")&&(order.contains("alt"))){
            List <ProductTop> tops = this.repository.getTopProductsDesc();
            List <Product> products = new ArrayList<>();
            for (ProductTop p:tops
                 ) {
                products.add(p.translateTopToProduct());
            }
            log.info("the most popular products are: "+products);
            return products;
        }
        if(option.equals("popularity")&&!(order.contains("alt"))){
            List <ProductTop> tops = this.repository.getTopProductsAsc();
            List <Product> products = new ArrayList<>();
            for (ProductTop p:tops
                    ) {
                products.add(p.translateTopToProduct());
            }
            log.info("the least popular products are: "+products);
            return products;
        }
        else return this.repository.findAllByOrderByNameAsc();
    }

    public BigDecimal getTotal(List<Product>products){

        BigDecimal total=BigDecimal.valueOf(0);
        List<BigDecimal> values=new ArrayList<>();

        if(!products.isEmpty()){

            products.forEach(product -> {values.add(product.getPrice());});
             total = values.stream().reduce((x, y) -> x.add(y)).get();
        }

        return total;
    }


    public void save(Product product) {
        this.repository.save(product);
    }

    public List<Product> findProducts() {

        return this.repository.findAllByOrderByNameAsc();

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

        return this.repository.getTopProductsDesc();
    }

}
