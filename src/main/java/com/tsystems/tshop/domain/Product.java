package com.tsystems.tshop.domain;

import com.tsystems.tshop.enums.ProductCategory;

public class Product {

	private String name;
	private Double price;
	private Double weight;
	private Integer inStock;
	private ProductCategory category;
	private Double volume;
	
	public Product() {}
	
	public Product(final String name,
			final Double price, 
			final Double weight, 
			final Integer inStock, 
			final ProductCategory category, 
			final Double volume) {
		super();
		this.name = name;
		this.price = price;
		this.weight = weight;
		this.inStock = inStock;
		this.category = category;
		this.volume = volume;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getInStock() {
		return inStock;
	}
	public void setInStock(Integer inStock) {
		this.inStock = inStock;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	
}
