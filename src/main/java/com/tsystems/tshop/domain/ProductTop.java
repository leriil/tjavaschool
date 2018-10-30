package com.tsystems.tshop.domain;

import java.math.BigDecimal;

public class ProductTop {

    private Long productId;

    private String name;

    private String category;

    private Integer inStock;

    private BigDecimal price;


    public ProductTop(Long productId, String name, String category,  Integer inStock, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.inStock = inStock;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
