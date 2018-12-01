package com.tsystems.tshop.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Profit {

    private LocalDate date;

    private BigDecimal profit;

    private Integer itemsSold;

    public Profit(LocalDate date, BigDecimal profit, Integer itemsSold) {
        this.date = date;
        this.profit = profit;
        this.itemsSold = itemsSold;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Integer getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(Integer itemsSold) {
        this.itemsSold = itemsSold;
    }
}
