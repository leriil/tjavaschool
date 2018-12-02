package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {

    ProductCategory findProductCategoryByCategoryName(String name);

}
