package com.tsystems.tshop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tsystems.tshop.enums.ProductCategory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
@SqlResultSetMapping(
        name="productTopMapping",
        classes = @ConstructorResult(
                        targetClass = ProductTop.class,
                        columns = {
                        @ColumnResult(name = "productId",type = Long.class),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "category"),
                        @ColumnResult(name = "inStock", type = Integer.class),
                        @ColumnResult(name = "price", type = BigDecimal.class),
                                @ColumnResult(name = "volume", type = Double.class),
                                @ColumnResult(name="weight",type = Double.class),
                                @ColumnResult(name="top",type = Integer.class)
}))
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;

	//means that the property may only be written (set) for deserialization,
	// but will not be read (get) on serialization, that is, the value of the
	// property is not included in serialization.
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(mappedBy = "products")
	private Set<Order> orders =new HashSet<>();

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

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}


	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Objects.equals(productId, product.productId) &&
				Objects.equals(name, product.name) &&
				Objects.equals(price, product.price) &&
				Objects.equals(weight, product.weight) &&
				Objects.equals(inStock, product.inStock) &&
				category == product.category &&
				Objects.equals(volume, product.volume);
	}

	@Override
	public int hashCode() {

		return Objects.hash(productId, name, price, weight, inStock, category, volume);
	}

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
