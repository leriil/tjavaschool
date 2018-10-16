package com.tsystems.tshop.domain;

import com.tsystems.tshop.enums.ProductCategory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;

	@ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
	private List<Sale> sales=new ArrayList<>();

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "in_stock")
	private Integer inStock;

	@Column(name = "category")
	@Enumerated(EnumType.STRING)
	private ProductCategory category;

	@Column(name = "volume")
	private Double volume;
	
	public Product() {}
	
//	public Product(final String name,
//			final Double price,
//			final Double weight,
//			final Integer inStock,
//			final ProductCategory category,
//			final Double volume) {
//		super();
//		this.name = name;
//		this.price = price;
//		this.weight = weight;
//		this.inStock = inStock;
//		this.category = category;
//		this.volume = volume;
//	}

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

//	public List<Sale> getSales() {
//		return sales;
//	}
//
//	public void setSales(List<Sale> sales) {
//		this.sales = sales;
//	}

//	public void addSale(Sale sale){
//		if(!sales.contains(sale)){
//			sales.add(sale);
//		}
//
//		sale.addProduct(this);
//	}

	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", name='" + name + '\'' +
				", price=" + price +
				", weight=" + weight +
				", inStock=" + inStock +
				", category=" + category +
				", volume=" + volume +
				'}';
	}
}
