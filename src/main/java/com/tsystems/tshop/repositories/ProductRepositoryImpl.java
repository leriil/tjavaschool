package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.ProductTop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    private static final String topProducts="select product_id as productId, name, product.category_id as categoryId, category_name as categoryName, in_stock as inStock, price, " +
            "volume, weight, top from product left join " +
            "(select product.product_id as pid, count(order_product.product_id) as top " +
            "from product join order_product " +
            "on product.product_id=order_product.product_id " +
            "group by pid) as s on product.product_id=s.pid " +
            "left join category on product.category_id=category.category_id ";

    private static final String orderAsc="order by top asc";

    private static final String orderDesc="order by top desc";

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductTop> getTopProductsDesc() {

        return
                em.createNativeQuery(topProducts+orderDesc,
                        "productTopMapping")
                        .getResultList();

    }

    @SuppressWarnings("unchecked")
    public List<ProductTop> getTopProductsAsc(){

        return
                em.createNativeQuery(topProducts+orderAsc,
                        "productTopMapping")
                        .getResultList();
    }

}
