package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,ProductRepositoryCustom {

    List<Product> findAllByOrderByNameAsc();
    List<Product> findProductsByNameContains(String name);
    List<Product>findAllByOrderByCategoryCategoryNameAsc();
    List<Product>findAllByOrderByCategoryCategoryNameDesc();





}
