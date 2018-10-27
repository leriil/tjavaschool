package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


//    @Query(
//            "select new com.tsystems.tshop.domain.ProductTop (p.productId, count(s.products.productId)) " +
//                    "from Product p join p.sales s  where p.productId = s.products.productId group by p.productId")
    List<Product> findAll();

    @Query("select p from Product p order by (p.sales)")
    List<Product> getTop();

//    @Query(value = "select * from product",
//            nativeQuery = true)
//    List<Product> getTop();


}
