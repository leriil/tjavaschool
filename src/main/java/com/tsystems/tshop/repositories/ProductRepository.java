package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,ProductRepositoryCustom {

    List<Product> findAllByOrderByNameAsc();
//    List<Product>findAllByOrderByNameDesc();
//    List<Product>findAllByOrderByPriceAsc();
//    List<Product>findAllByOrderByPriceDesc();
    List<Product> findProductsByNameContains(String name);


//    @Query("select p from Product p order by p.price asc ")
//    List<Product>findAll(new Sort(Sort.Direction.ASC,"price"));

//    @Query("select p from Product p order by p.price desc ")
//    List<Product>findAllOrderByPriceDesc();




}
