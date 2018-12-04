package com.tsystems.tshop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
@SqlResultSetMapping(
        name = "productTopMapping",
        classes = @ConstructorResult(
                targetClass = ProductTop.class,
                columns = {
                        @ColumnResult(name = "productId", type = Long.class),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "categoryId", type = Long.class),
                        @ColumnResult(name = "categoryName"),
                        @ColumnResult(name = "inStock", type = Integer.class),
                        @ColumnResult(name = "price", type = BigDecimal.class),
                        @ColumnResult(name = "color"),
                        @ColumnResult(name = "weight", type = Double.class),
                        @ColumnResult(name = "top", type = Integer.class)
                }))
@XmlRootElement
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();

    @NotBlank(message = "A product should have a name")
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "in_stock")
    private Integer inStock;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @Column(name = "color")
    private String color;

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    @XmlElement
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    @XmlElement
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getInStock() {
        return inStock;
    }

    @XmlElement
    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public ProductCategory getCategory() {
        return category;
    }

    @XmlElement
    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getColor() {

        return color;
    }
    @XmlElement
    public void setColor(String color) {

        this.color = color;
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
                Objects.equals(category, product.category) &&
                Objects.equals(color, product.color);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, name, price, weight, inStock, category, color);
    }
}
