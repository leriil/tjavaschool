package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.ProductTop;

import java.util.List;

public interface ProductRepositoryCustom {

    public List<ProductTop> getTopProductsDesc();

    public  List<ProductTop> getTopProductsAsc();

}
