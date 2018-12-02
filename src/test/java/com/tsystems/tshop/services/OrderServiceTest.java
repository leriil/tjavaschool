package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.*;
import com.tsystems.tshop.enums.OrderStatus;
import com.tsystems.tshop.enums.PaymentStatus;
import com.tsystems.tshop.repositories.OrderRepository;
import com.tsystems.tshop.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    OrderService orderService;
    Card card;
    Order order;
    Order order1;
    Order order2;
    List<Product> products;
    Product product;
    User user;
    Address address;
    LocalDate today;
    LocalDate thisWeek;
    LocalDate lastWeek;
    LocalDate twoWeeksAgo;
    @Mock
    private UserService userService;
    @Mock
    private AddressService addressService;
    @Mock
    private ProductService productService;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void init() {

        MockitoAnnotations.initMocks(this);
        orderService = new OrderService(orderRepository, userService, addressService, productService, paymentRepository);
        card = new Card();
        card.setCardNumber(1L);
        order = new Order();
        order.setOrderId(1L);
        product = new Product();
        product.setProductId(1L);
        products = new ArrayList<>();
        products.add(product);
        user = new User();
        user.setUserId(1L);
        address = new Address();
        address.setAddressId(1L);
        today = LocalDate.now();
        thisWeek = today.minusDays(5);
        lastWeek = today.minusDays(10);
        twoWeeksAgo = today.minusDays(15);
        order.setOrderDate(thisWeek);
        order1 = new Order();
        order1.setOrderDate(lastWeek);
        order2 = new Order();
        order2.setOrderDate(twoWeeksAgo);

    }

    @Test
    void payWithCard_whenInvalidData_thenThrowRuntimeException() {

        lenient().when(paymentRepository.processCard(new CardWithdrawal()))
                .thenReturn(new ResponseEntity<>(HttpStatus.FORBIDDEN));

        assertThrows(RuntimeException.class, () -> orderService.payWithCard(card, order, products));
    }

    @Test
    void payWithCard_whenValidData_thenSetPaymentStatus() {

        user.setAddress(address);
        order.setUser(user);

        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);
        when(paymentRepository.processCard(any(CardWithdrawal.class))).thenReturn(response);

        orderService.payWithCard(card, order, products);

        PaymentStatus status = order.getPaymentStatus();
        assertEquals(PaymentStatus.PAID, status);

    }

    @Test
    void saveOrder() {

    }

    @Test
    void findUserOrders() {

    }

    @Test
    void findOrder_givenValidId_thenReturnOrder() throws Exception {

        Long orderId = 10L;
        Order order = new Order();
        order.setOrderId(10L);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        orderService.findOrder(orderId);
        verify(orderRepository).findById(orderId);
    }

    @Test()
    void findOrder_givenInvalidId_thenThrowException() {

        Long orderId = 100L;
        Order order = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> orderService.findOrder(orderId));
        verify(orderRepository).findById(orderId);
    }

    @Test
    void updateOrder() {

        Order order = new Order();
        order.setOrderId(1L);
        when(orderRepository.save(order)).thenReturn(order);
        orderService.updateOrder(order);
        verify(orderRepository).save(order);
    }

    @Test
    void createNewOrder() {

        order.setAddress(address);

        Order created = orderService.createNewOrder(order);
        assertAll(
                () -> assertNotNull(created.getAddress()),
                () -> assertEquals(PaymentStatus.NOT_PAID, created.getPaymentStatus()),
                () -> assertEquals(OrderStatus.PENDING_APPROVAL, created.getOrderStatus()),
                () -> assertEquals(LocalDate.now(), created.getOrderDate())
        );
    }

    @Test
    void createNewOrder_whenGivenOrderWithId_thenReturnNewOrderWithoutId() {

        Order created = orderService.createNewOrder(order);
        assertNull(created.getOrderId());

    }

    @Test
    void createNewOrder_whenGivenOrderWithoutId_thenReturnNewOrderWithoutId() {

        lenient().when(userService.getUser()).thenReturn(new User());

        Order created = orderService.createNewOrder(order);
        assertNull(created.getOrderId());

    }

    @Test
    void getProductsByOrderId_whenOrderExists_thenReturnProducts() {

        order.setProducts(products);
        lenient().when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
        assertEquals(products, orderService.getProductsByOrderId(order.getOrderId()));
    }

    @Test
    void getProductsByOrderId_whenOrderDoesNotExist_thenReturnEmptyList() {

        List<Product> products = new ArrayList<>();
        lenient().when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.empty());
        List<Product> actualProducts = orderService.getProductsByOrderId(order.getOrderId());
        assertEquals(products, actualProducts);
    }

    @Test
    void getOrdersWithDatesBetween_whenBothDatesAreNull_thenReturnOrdersForLastWeek() {

        lenient().when(orderRepository.findAllByOrderDateBetween(today.minusDays(7), today))
                .thenReturn(Arrays.asList(order));
        List<Order> actualOrders = orderService.getOrdersWithDatesBetween(null, null);
        assertEquals(Arrays.asList(order), actualOrders);

    }

    @Test
    void getOrdersWithDatesBetween_whenFirstDayIsNull_thenReturnOrdersForLastWeek() {

        lenient().when(orderRepository.findAllByOrderDateBetween(today.minusDays(7), today))
                .thenReturn(Arrays.asList(order));
        List<Order> actualOrders = orderService.getOrdersWithDatesBetween(thisWeek, null);
        assertEquals(Arrays.asList(order), actualOrders);

    }

    @Test
    void getOrdersWithDatesBetween_whenLastDayIsNull_thenReturnOrdersForLastWeek() {

        lenient().when(orderRepository.findAllByOrderDateBetween(today.minusDays(7), today))
                .thenReturn(Arrays.asList(order));
        List<Order> actualOrders = orderService.getOrdersWithDatesBetween(null, today);
        assertEquals(Arrays.asList(order), actualOrders);

    }

    @Test
    void getOrdersWithDatesBetween_whenBothDatesAreNotNull_thenReturnOrdersForThisPeriod() {

        lenient().when(orderRepository.findAllByOrderDateBetween(twoWeeksAgo, thisWeek))
                .thenReturn(Arrays.asList(order2, order1));
        List<Order> actualOrders = orderService.getOrdersWithDatesBetween(twoWeeksAgo, thisWeek);
        assertEquals(Arrays.asList(order2, order1), actualOrders);

    }


    @Test
    void getOrders() {

    }

    //    public List<Profit> getProfit(LocalDate firstDay, LocalDate lastDay, String period) {
//        if ("".equals(period)) {
//            return orderRepository.getProfit(firstDay, lastDay);
//        } else {
//            LocalDate last = LocalDate.now();
//            LocalDate first = null;
//            if (period.contains("week")) {
//                first = last.minusDays(7);
//            } else if (period.contains("month")) {
//                first = last.minusDays(last.lengthOfMonth());
//            } else if (period.contains("year")) {
//                first = last.minusDays(last.lengthOfYear());
//            }
//            return orderRepository.getProfit(first, last);
//        }
//    }
    @Test
    void getProfit() {

    }

    @Test
    void addProductToCart() {

    }

    @Test
    void removeProductFromCart() {

    }
}