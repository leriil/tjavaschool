package com.tsystems.tshop.repositories.impl;

import com.tsystems.tshop.domain.UserTop;
import com.tsystems.tshop.repositories.UserRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {

    private static final String BEST_CLIENTS = "select buyer as userId, user.name, user.surname, user.email, money_spent as moneySpent from " +
            "(select sum(product.price) as money_spent, user_id as buyer from " +
            "(select order_.order_id, user_id, product_id " +
            "from order_ join order_product " +
            "on order_.order_id=order_product.order_id) " +
            "as o join product on o.product_id=product.product_id group by buyer) as purchases " +
            "join user on purchases.buyer=user.user_id ";
    private static final String ORDER_ASC = "order by moneySpent asc";
    private static final String ORDER_DESC = "order by moneySpent desc";
    @PersistenceContext
    private
    EntityManager em;

    @SuppressWarnings("unchecked")
    public List<UserTop> getTopUsersAsc(){
        return
                em.createNativeQuery(BEST_CLIENTS + ORDER_ASC, "userTopMapping")
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<UserTop> getTopUsersDesc(){
        return
                em.createNativeQuery(BEST_CLIENTS + ORDER_DESC, "userTopMapping")
                        .getResultList();
    }

}
