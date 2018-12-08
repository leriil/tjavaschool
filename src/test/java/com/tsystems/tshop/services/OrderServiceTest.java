package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.*;
import com.tsystems.tshop.enums.OrderStatus;
import com.tsystems.tshop.enums.PaymentStatus;
import com.tsystems.tshop.repositories.OrderRepository;
import com.tsystems.tshop.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

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
    SecurityContext securityContext;
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
    @Mock
    private Authentication authentication;

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
        user.setLogin("bill");
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
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
        lenient().when(securityContext.getAuthentication().getName()).thenReturn(user.getLogin());
        SecurityContextHolder.setContext(securityContext);

    }

    @Test
    void payWithCard_whenInvalidData_thenThrowRuntimeException() {

        lenient().when(paymentRepository.processCard(new CardWithdrawal()))
                .thenReturn(new ResponseEntity<>(HttpStatus.FORBIDDEN));

        assertThrows(RuntimeException.class, () -> orderService.payWithCard(card, order, products));
    }

    @Test
    void payWithCard_whenValidData_thenSetPaymentStatus() throws Exception {

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
    void findUserOrders_whenUserAuthenticated_thenReturnTheirOrders() {

        Set<Order> orderSet = new HashSet<>();
        orderSet.add(order);
        lenient().when(orderRepository.findAllByUser_Login(any(String.class))).thenReturn(orderSet);
        assertTrue(!orderService.findUserOrders().isEmpty());
        verify(orderRepository).findAllByUser_Login(anyString());

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
    void createNewOrder_whenGivenOrderWithoutIdAndUserHasAddress_thenSetOrderAddress() {

        order.setOrderId(null);
        user.setAddress(address);
        lenient().when(userService.getUser()).thenReturn(user);
        assertAll(
                () -> assertTrue(Objects.nonNull(user.getAddress())),
                () -> assertEquals(user.getAddress().getAddressId(), orderService.createNewOrder(order).getAddress().getAddressId())
        );
    }

    @Test
    void getProductsByOrderId_whenOrderExists_thenReturnProducts() {

        order.setProducts(products);
        lenient().when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
        assertAll(
                () -> assertEquals(products, orderService.getProductsByOrderId(order.getOrderId())),
                () -> assertTrue(Objects.nonNull(orderService.getProductsByOrderId(order.getOrderId())))
        );
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
        verify(orderRepository).findAllByOrderDateBetween(today.minusDays(7), today);
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

        List<Order> allOrders = new ArrayList<>();
        allOrders.add(order);
        allOrders.add(order1);
        lenient().when(orderRepository.findAll()).thenReturn(allOrders);
        assertEquals(orderService.getOrders(), allOrders);
    }

    @Nested
    class cartTest{

        List<Product> cart;

        @BeforeEach
        void init() {

            cart = new ArrayList<>();
            product.setInStock(20);
            Product product1 = new Product();
            product1.setProductId(2L);
            product1.setInStock(15);
            cart.add(product);
            cart.add(product1);
        }

        @Test
        void addProductToCart() {

            when(productService.findProductById(product.getProductId())).thenReturn(product);
            List<Product> productsInCart = orderService.addProductToCart(cart,1L);
            int actualInStock = productsInCart.get(0).getInStock();
            int expectedInStock = 19;
            assertAll(
                    ()->assertEquals(cart.size(), productsInCart.size()),
                    ()-> assertEquals(expectedInStock,actualInStock)
            );
        }

        @Test
        void removeProductFromCart() {
            List <Product> productsInCart = orderService.removeProductFromCart(cart,product.getProductId());
            int actualInStock = product.getInStock();
            int expectedInStock = 21;
            assertAll(
                    ()->assertEquals(productsInCart.size(), cart.size()),
                    ()->assertEquals(expectedInStock,actualInStock)
            );
        }
    }

    @Nested
    class ProfitTest {

        List<Profit> expectedProfitList;


        @BeforeEach
        void init() {

            expectedProfitList = new ArrayList<>();
        }

        @Test
        void getProfit_whenPeriodIsNull_thenReturnProfitForDates() {

            int difference = lastWeek.compareTo(thisWeek);
            Profit profit = new Profit(lastWeek.plusDays(difference), new BigDecimal(10), 20);
            profit.setDate(lastWeek.plusDays(difference));
            expectedProfitList.add(profit);
            lenient().when(orderRepository.getProfit(lastWeek, thisWeek)).thenReturn(expectedProfitList);
            assertEquals(expectedProfitList, orderService.getProfit(lastWeek, thisWeek, new String()));

        }

        @Test
        void getProfit_whenPeriodIsWeek_thenReturnProfitForLastWeek(){
            Profit profit = new Profit(today.minusDays(1), new BigDecimal(10), 20);
            expectedProfitList.add(profit);
            when(orderRepository.getProfit(today.minusDays(7),today)).thenReturn(expectedProfitList);
            List<Profit> actualProfitList = orderService.getProfit(null,null,"week");
            assertEquals(actualProfitList,expectedProfitList);
        }

        @Test
        void getProfit_whenPeriodIsMonth_thenReturnProfitForLastMonth(){
            Profit profit = new Profit(today.minusDays(20), new BigDecimal(10), 20);
            expectedProfitList.add(profit);
            when(orderRepository.getProfit(today.minusDays(today.lengthOfMonth()),today)).thenReturn(expectedProfitList);
            List<Profit> actualProfitList = orderService.getProfit(null,null,"month");
            assertEquals(actualProfitList,expectedProfitList);
        }

        @Test
        void getProfit_whenPeriodIsYear_thenReturnProfitForLastYear(){
            Profit profit = new Profit(today.minusDays(60), new BigDecimal(10), 20);
            expectedProfitList.add(profit);
            when(orderRepository.getProfit(today.minusDays(today.lengthOfYear()),today)).thenReturn(expectedProfitList);
            List<Profit> actualProfitList = orderService.getProfit(null,null,"year");
            assertEquals(actualProfitList,expectedProfitList);
        }
    }
}