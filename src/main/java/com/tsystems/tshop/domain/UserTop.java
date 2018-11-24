package com.tsystems.tshop.domain;

import java.math.BigDecimal;

public class UserTop {

    private Long userId;

    private String name;

    private String surname;

    private String email;

    private BigDecimal moneySpent;

    public UserTop(Long userId, String name, String surname, String email, BigDecimal moneySpent) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.moneySpent = moneySpent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(BigDecimal moneySpent) {
        this.moneySpent = moneySpent;
    }
}
