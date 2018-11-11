package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.domain.ProductTop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    private static final String topProducts="select product_id as productId, name, category, in_stock as inStock, price, volume, weight, top " +
            "from(select * from product left join " +
            "(select product.product_id as pid, count(order_product.product_id) as top " +
            "from product join order_product " +
            "on product.product_id=order_product.product_id " +
            "group by pid) as s on product.product_id=s.pid) as p ";

    private static final String orderAsc="order by p.top asc";

    private static final String orderDesc="order by p.top desc";

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

    //TODO: why doesn't this work??
    @SuppressWarnings("unchecked")
    public List<Product> getTop(){

        return (List<Product>)
                em.createNativeQuery(topProducts+orderDesc,
                        Product.class)
                        .getResultList();
    }
}
