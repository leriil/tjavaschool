package com.tsystems.tshop.domain;

import java.math.BigDecimal;

public class ProductTop {

    private Long productId;

    private String name;

    private Long categoryId;

    private String categoryName;

    private Integer inStock;

    private BigDecimal price;

    private String color;

    private Double weight;

    private Integer top;

    public ProductTop(Long productId,
                      String name,
                      Long categoryId,
                      String categoryName,
                      Integer inStock,
                      BigDecimal price,
                      String color,
                      Double weight,
                      Integer top) {
        this.productId = productId;
        this.name = name;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.inStock = inStock;
        this.price = price;
        this.color = color;
        this.weight = weight;
        this.top = top;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

}
