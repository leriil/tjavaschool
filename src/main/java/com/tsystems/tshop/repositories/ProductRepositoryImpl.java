package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.ProductTop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//in the example there are @Repository and @Transactional here, but everything works without them
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<ProductTop> getTopProducts() {

        List<ProductTop> topProducts=
                em.createNativeQuery("select product_id as productId, name, category, in_stock as inStock, price " +
                "from(select * from product left join " +
                "(select product.product_id as pid, count(sale_product.product_id) as top " +
                "from product join sale_product " +
                "on product.product_id=sale_product.product_id " +
                "group by pid) as s on product.product_id=s.pid) as p order by p.top desc",
                        "productTopMapping")
                        .setMaxResults(2)
                        .getResultList();

        return topProducts;
    }
}
