package com.tsystems.tshop.repositories.impl;

import com.tsystems.tshop.domain.Profit;
import com.tsystems.tshop.repositories.OrderRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private static final String PROFIT_QUERY = "select p.order_date as date, sum(p.price) as profit, count(pid) itemsSold " +
            "from (select * from (select order_.order_id, user_id, product_id as pid, order_date " +
            "from order_ join order_product on order_.order_id=order_product.order_id where order_.payment_status='PAID') " +
            "as o join product on product.product_id=o.pid where order_date " +
            "between :first and :last) as p group by p.order_date";
    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Profit> getProfit(LocalDate first, LocalDate last) {

        return
                em.createNativeQuery(PROFIT_QUERY,
                        "profitMapping")
                        .setParameter("first", first)
                        .setParameter("last", last)
                        .getResultList();

    }

}
