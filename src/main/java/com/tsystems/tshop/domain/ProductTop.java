package com.tsystems.tshop.domain;

import com.tsystems.tshop.enums.ProductCategory;

import java.math.BigDecimal;

public class ProductTop {

    private Long productId;

    private String name;

    private String category;

    private Integer inStock;

    private BigDecimal price;

    private Double volume;

    private Double weight;

    private Integer top;


    public ProductTop(Long productId,
                      String name,
                      String category,
                      Integer inStock,
                      BigDecimal price,
                      Double volume,
                      Double weight,
                      Integer top)
    {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.inStock = inStock;
        this.price = price;
        this.volume = volume;
        this.weight = weight;
        this.top=top;
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

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
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

    //TODO:delete this if not used
    public Product translateTopToProduct(){

        Product product = new Product();
        product.setName(this.getName());
        product.setProductId(this.getProductId());
        product.setInStock(this.getInStock());
        product.setCategory(ProductCategory.valueOf(this.getCategory()));
        product.setPrice(this.getPrice());
        product.setWeight(this.getWeight());
        product.setVolume(this.getVolume());

        return product;
    }
}
