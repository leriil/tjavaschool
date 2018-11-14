package com.tsystems.tshop.domain;

import java.math.BigDecimal;

public class CardWithdrawal {

    private Card card;

    private BigDecimal total;

    public CardWithdrawal(Card card, BigDecimal total) {
        this.card = card;
        this.total = total;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
