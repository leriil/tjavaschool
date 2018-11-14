package com.tsystems.tshop.domain;

import com.tsystems.tshop.enums.OrderStatus;
import com.tsystems.tshop.enums.PaymentStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="order_product", joinColumns=@JoinColumn(name="order_id"),
//            inverseJoinColumns=@JoinColumn(name="product_id"))
//    private List<User> products=new ArrayList<>();
//@ManyToMany(cascade = {
//        CascadeType.ALL
//})
//@ManyToMany(cascade = CascadeType.ALL)
    @ManyToMany( fetch = FetchType.LAZY)
@JoinTable(name = "order_product",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
)
private List<Product> products = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToOne( fetch = FetchType.LAZY)
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
    @Enumerated (EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
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


    @Override
    public String toString() {
        return "Order{" +
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
