package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,ProductRepositoryCustom {

//    @Query(
//            "select new com.tsystems.tshop.domain.ProductTop (p.productId, count(s.products.productId)) " +
//                    "from Product p join p.sales s  where p.productId = s.products.productId group by p.productId")



//    @Query(value = "select * from product",
//            nativeQuery = true)
//
//    List<Product> getTop();


}
