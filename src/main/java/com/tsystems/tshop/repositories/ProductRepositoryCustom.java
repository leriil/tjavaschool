package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.ProductTop;

import java.util.List;

public interface ProductRepositoryCustom {

    List<ProductTop> getTopProductsDesc();

    List<ProductTop> getTopProductsAsc();

}
