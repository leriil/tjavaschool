package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.domain.ProductTop;

import java.util.List;

public interface ProductRepositoryCustom {

    public List<ProductTop> getTopProductsDesc();

    public  List<ProductTop> getTopProductsAsc();

    //TODO: find out why it throws sqlgrammar exception, no column with name "product_id"
    public List<Product> getTop();

}
