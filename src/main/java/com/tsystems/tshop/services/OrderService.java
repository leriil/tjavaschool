package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.*;
import com.tsystems.tshop.enums.OrderStatus;
import com.tsystems.tshop.enums.PaymentStatus;
import com.tsystems.tshop.repositories.OrderRepository;
import com.tsystems.tshop.repositories.PaymentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

/**
 *
 */
@Service
@Transactional
public class OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final AddressService addressService;
    private final ProductService productService;
    private final PaymentRepository paymentRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        UserService userService,
                        AddressService addressService,
                        ProductService productService,
                        PaymentRepository paymentRepository) {

        this.orderRepository = orderRepository;
        this.userService = userService;
        this.addressService = addressService;
        this.productService = productService;
        this.paymentRepository = paymentRepository;
    }


    /**
     * Creates a CardWithdrawal object which is passed to PaymentRepository
     * in an HttpEntity via RestTemplate. If the response.statusCode() is ok,
     * sets order's paymentStatus to PAID and passes the order and the products
     * to saveOrder(Order order, List <Product> products) method.
     *
     * @param card     used to create a CardWithdrawal object
     * @param order    saved to the database if payment process is successful
     * @param products a total for this list is estimated and used to create a CardWithrawal object
     * @throws RuntimeException if the statusCode() of the return value of the RestTemplate object
     *                          is not HttpStatus.OK.
     */

    public void payWithCard(Card card, Order order, List<Product> products)  {

        CardWithdrawal data = new CardWithdrawal(card, productService.getTotal(products));

        ResponseEntity<String> response = paymentRepository.processCard(data);

        LOGGER.info("Response status from PaymentRepository: {}", response.getStatusCode());
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            order.setPaymentStatus(PaymentStatus.PAID);
            this.saveOrder(order, products);

        } else throw new RuntimeException();
    }


    /**
     * Saves an order to the a database.
     * @param order    an order to be saved
     * @param products products to be associated with that order
     */

    public void saveOrder(Order order, List<Product> products) {

        addressService.save(order.getAddress());
        order.getUser().setAddress(order.getAddress());
        userService.saveUser(order.getUser());
        order.setProducts(products);
        orderRepository.save(order);
        LOGGER.info("Order saved: {} ", order);
    }


    /**
     * @return a Set of orders placed by the user
     */
    public Set<Order> findUserOrders() {

        final String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Set<Order> orders = orderRepository.findAllByUser_Login(login);
        LOGGER.info("user's orders with login {} are {}", login, orders);
        return orders;
    }

    /**
     * @param id used to find an order with that id in the database
     * @return an order with the specified id
     * @throws Exception if the order with the id is not found
     */
    public Order findOrder(Long id) throws Exception {

        Optional<Order> order = this.orderRepository.findById(id);

        if (order.isPresent()) {
            LOGGER.info("Order with id {} was found: {}", id, order);
            return order.get();

        } else {
            LOGGER.info("There is no order with id: " + id.toString());
            throw new Exception();
        }
    }


    /**
     * @param order is saved to the database
     */
    public void updateOrder(Order order) {

        orderRepository.save(order);
    }

    /**
     * If the order has an id, than it is set to <tt>null</tt>.
     * The order is associated with a user and their current address
     * and a current date. OrderStatus is set to PENDING_APPROVAL,
     * PaymentStatus is set to NOT_PAID.
     *
     * @param order is used to create a new order
     * @return returns the order that has been created.
     */
    public Order createNewOrder(Order order) {

        if (Objects.nonNull(order.getOrderId())) {
            order.setOrderId(null);
        } else {
            User user = this.userService.getUser();
            order.setUser(user);
            Address currentAddress = user.getAddress();
            if (Objects.nonNull(currentAddress)) {
                order.setAddress(currentAddress);

            }
        }

        LocalDate date = LocalDate.now();
        LOGGER.info("date of order is " + date);
        order.setOrderDate(date);
        order.setOrderStatus(OrderStatus.PENDING_APPROVAL);
        order.setPaymentStatus(PaymentStatus.NOT_PAID);
        LOGGER.info("after creating a new order: " + order);
        return order;
    }

    /**
     * @param orderId used to find an order that has an associated List of products
     * @return a list of products in the order
     */
    public List<Product> getProductsByOrderId(Long orderId) {

        List<Product> products = new ArrayList<>();
        Optional<Order> order = this.orderRepository.findById(orderId);
        if (order.isPresent()) {
            products = order.get().getProducts();
            LOGGER.info("Products for order with id {} are: {} ", orderId, products);
        }
        return products;
    }

    /**
     * Returns a List of orders placed during a certain period of time.
     * If the first and last days of the period are not specified,
     * looks for orders placed during the last week.
     *
     * @param first the first day of the period
     * @param last  the last day of the perid
     * @return a list of orders placed during a certain period
     */
    public List<Order> getOrdersWithDatesBetween(LocalDate first, LocalDate last) {

        if (Objects.isNull(first) || Objects.isNull(last)) {
            last = LocalDate.now();
            first = last.minusDays(7);
        }
        List<Order> orders = orderRepository.findAllByOrderDateBetween(first, last);
        LOGGER.info("orders between dates {} and {} are: {}", first, last, orders);
        return orders;
    }

    /**
     * @return a list of all orders from the database
     */
    public List<Order> getOrders() {

        return orderRepository.findAll();
    }

    /**
     * Returns a List of Profit objects for a certain period of time.
     * If the period is specified: <i>week</i>,<i>month</i>,<i>year</i>,
     * than it uses that period. Otherwise the period is calculated with the
     * passed parameters.
     *
     * @param firstDay first day of the period
     * @param lastDay  last day of the period
     * @param period   a period of time for which a search should be performed
     * @return a List of Profit objects for a particular period of time
     */
    public List<Profit> getProfit(LocalDate firstDay, LocalDate lastDay, String period) {

        if ("".equals(period)) {
            return orderRepository.getProfit(firstDay, lastDay);
        } else {
            LocalDate last = LocalDate.now();
            LocalDate first = null;
            if (period.contains("week")) {
                first = last.minusDays(7);
            } else if (period.contains("month")) {
                first = last.minusDays(last.lengthOfMonth());
            } else if (period.contains("year")) {
                first = last.minusDays(last.lengthOfYear());
            }
            return orderRepository.getProfit(first, last);
        }
    }

    /**
     * Looks for a product with a particular id, adds it the users cart
     * and sets its inStock value to inStock-1.
     *
     * @param cart      user's shopping cart where a product should be added
     * @param productId a id of the product which is added to the cart
     * @return returns a List of products (a shopping cart) with a new product
     */
    public List<Product> addProductToCart(List<Product> cart, Long productId) {

        Product product = productService.findProductById(productId);
        product.setInStock(product.getInStock() - 1);
        cart.stream()
                .filter(p -> p.getProductId().equals(productId))
                .forEach(p -> p.setInStock(product.getInStock()));
        cart.add(product);
        productService.save(product);
        return cart;
    }

    /**
     * Looks for a product with a particular id in the user's shopping cart.
     * If any product with such an id is found, it is removed from the shopping cart
     * and returned to the database. The inStock value of the product is increased by one.
     *
     * @param productsInCart a user's shopping cart
     * @param productId      an id of the product to be removed from the database
     * @return
     */
    public List<Product> removeProductFromCart(List<Product> productsInCart, Long productId) {

        final Optional<Product> product = productsInCart.stream()
                .filter(p -> p.getProductId().equals(productId)).findAny();
        if (product.isPresent()) {
            Product productInCart = product.get();
            productsInCart.remove(productInCart);
            productInCart.setInStock(productInCart.getInStock() + 1);
            productService.save(productInCart);
            productsInCart.stream()
                    .filter(p -> p.getProductId().equals(productId))
                    .forEach(p -> p.setInStock(productInCart.getInStock()));

            LOGGER.info("product {} has been removed from cart", productInCart);
        }
        return productsInCart;
    }
}

