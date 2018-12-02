package com.tsystems.tshop.repositories.impl;

import com.tsystems.tshop.domain.ProductTop;
import com.tsystems.tshop.repositories.ProductRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private static final String TOP_PRODUCTS = "select product_id as productId, name, product.category_id as categoryId, category_name as categoryName, in_stock as inStock, price, " +
            "volume, weight, top from product left join " +
            "(select product.product_id as pid, count(order_product.product_id) as top " +
            "from product join order_product " +
            "on product.product_id=order_product.product_id " +
            "group by pid) as s on product.product_id=s.pid " +
            "left join category on product.category_id=category.category_id ";
    private static final String ORDER_ASC = "order by top asc";
    private static final String ORDER_DESC = "order by top desc";
    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductTop> getTopProductsDesc() {

        return
                em.createNativeQuery(TOP_PRODUCTS + ORDER_DESC,
                        "productTopMapping")
                        .getResultList();

    }

    @SuppressWarnings("unchecked")
    public List<ProductTop> getTopProductsAsc() {

        return
                em.createNativeQuery(TOP_PRODUCTS + ORDER_ASC,
                        "productTopMapping")
                        .getResultList();
    }

}
