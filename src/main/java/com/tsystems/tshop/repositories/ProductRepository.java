package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,ProductRepositoryCustom {

    List<Product> findAllByOrderByNameAsc();
//    List<Product>findAllByOrderByNameDesc();
//    List<Product>findAllByOrderByPriceAsc();
//    List<Product>findAllByOrderByPriceDesc();
    List<Product> findProductsByNameContains(String name);
    List<Product>findAllByOrderByCategoryCategoryNameAsc();
    List<Product>findAllByOrderByCategoryCategoryNameDesc();


    @Query("select p from Product p left join p.category c ")
    List<Product>findAllProductsOrderByNameAsc(Sort sort);

    @Query("select p from Product p left join p.category c where p.name =:name ")
    List<Product>findAllOrderByPriceDesc(String name);




}
