package com.tsystems.tshop.domain;

public class ProductTop {

    private Long productId;
    private int numberOfSoldItems;

    public ProductTop(Long productId, int numberOfSoldItems) {
        this.productId = productId;
        this.numberOfSoldItems=numberOfSoldItems;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getNumberOfSoldItems() {
        return numberOfSoldItems;
    }

    public void setNumberOfSoldItems(int numberOfSoldItems) {
        this.numberOfSoldItems = numberOfSoldItems;
    }
}
