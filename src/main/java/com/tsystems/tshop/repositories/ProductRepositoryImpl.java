package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//in the example there are @Repository and @Transactional here, but everything works without them
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Product> getTopProducts() {

        javax.persistence.Query query10TopProducts=
                em.createNativeQuery("select product_id, category, in_stock, name, price, volume, weight " +
                "from(select * from product left join " +
                "(select product.product_id as pid, count(sale_product.product_id) as top " +
                "from product join sale_product " +
                "on product.product_id=sale_product.product_id " +
                "group by pid) as s on product.product_id=s.pid) as p order by p.top desc");

        List<Product> topProducts=query10TopProducts.setMaxResults(2).getResultList();

        return topProducts;
    }
}
