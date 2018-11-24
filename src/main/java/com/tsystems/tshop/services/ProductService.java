package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.domain.ProductTop;
import com.tsystems.tshop.repositories.CategoryRepository;
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
    CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository repository,
                          CategoryRepository categoryRepository) {
        this.categoryRepository=categoryRepository;
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
        else return this.repository.findAll();
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
        categoryRepository.save(product.getCategory());
        repository.save(product);
    }

    public List<Product> findProducts() {

        return repository.findAll();

    }

    public Product findProductById(Long productId) throws RuntimeException {
        Optional<Product> product = this.repository.findById(productId);
        if (product.isPresent()) {
            return product.get();
        } else
        {log.log(Level.WARNING,"There is no product with id: "+productId.toString());
            throw new RuntimeException();}
    }

    public List<Product> findProductByName(String name) {
       return repository.findProductsByNameContains(name);

    }

    @Transactional
    public List<ProductTop> getTopProducts(String order){

        if(!order.contains("alt")){
            return repository.getTopProductsAsc();
        }else return this.repository.getTopProductsDesc();
    }

    public List<Product>sortProducts(){
        return this.repository.findAllByOrderByNameAsc();
    }

    public List<Product> sortProducts(String columnName, String sortingOrder) {
        if(columnName==null){
            return this.repository.findAll();
        }
        else if (sortingOrder.equals("asc")) {
            if (columnName.equals("Name")) {
                return repository.findAll(new Sort(Sort.Direction.ASC,"name"));
            }
            if (columnName.equals("Price")) {
                return repository.findAll(new Sort(Sort.Direction.ASC,"price"));
            }
            if (columnName.equals("Weight")) {
                return repository.findAll(new Sort(Sort.Direction.ASC,"weight"));
            }
            if (columnName.equals("Volume")) {
                return repository.findAll(new Sort(Sort.Direction.ASC,"volume"));
            }
            if (columnName.equals("in Stock")) {
                return repository.findAll(new Sort(Sort.Direction.ASC,"inStock"));
            }
            if (columnName.equals("Category")) {
//
                return repository.findAllByOrderByCategoryCategoryNameAsc();
            }

        }
        else if (sortingOrder.equals("desc")) {
            if (columnName.equals("Name")) {
                return repository.findAll(new Sort(Sort.Direction.DESC,"name"));
            }
            if (columnName.equals("Price")) {
                return repository.findAll(new Sort(Sort.Direction.DESC,"price"));
            }
            if (columnName.equals("Weight")) {
                return repository.findAll(new Sort(Sort.Direction.DESC,"weight"));
            }
            if (columnName.equals("Volume")) {
                return repository.findAll(new Sort(Sort.Direction.DESC,"volume"));
            }
            if (columnName.equals("in Stock")) {
                return repository.findAll(new Sort(Sort.Direction.DESC,"inStock"));
            }
            if (columnName.equals("Category")) {

                return repository.findAllByOrderByCategoryCategoryNameDesc();
            }
        }
       return this.repository.findAll();
    }

}
