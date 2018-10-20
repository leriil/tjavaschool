package com.tsystems.tshop.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long orderId;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="order_product", joinColumns=@JoinColumn(name="order_id"),
//            inverseJoinColumns=@JoinColumn(name="product_id"))
//    private List<User> products=new ArrayList<>();
//@ManyToMany(cascade = {
//        CascadeType.ALL
//})
//@ManyToMany(cascade = CascadeType.ALL)
    @ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name = "sale_product",
        joinColumns = @JoinColumn(name = "sale_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
)
private Set<Product> products = new HashSet<>();

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "delivery_method")
    private String deliveryMethod;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "order_status")
    private String orderStatus;

//    public Long getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Long orderId) {
//        this.orderId = orderId;
//    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public void addProduct(Product product){
//        if(!products.contains(product)){
//            products.add(product);
//        }
//        product.addSale(this);
//    }

    @Override
    public String toString() {
        return "Sale{" +
                "orderId=" + orderId +
                ", products=" + products +
                ", address=" + address +
                ", user=" + user +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
