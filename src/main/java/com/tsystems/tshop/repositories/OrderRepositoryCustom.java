package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.Profit;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepositoryCustom {
    List<Profit> getProfit(LocalDate first, LocalDate last);
}
