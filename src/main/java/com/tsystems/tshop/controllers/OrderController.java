package com.tsystems.tshop.controllers;

import com.tsystems.tshop.domain.Card;
import com.tsystems.tshop.domain.Order;
import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.domain.Profit;
import com.tsystems.tshop.enums.OrderStatus;
import com.tsystems.tshop.enums.PaymentStatus;
import com.tsystems.tshop.services.OrderService;
import com.tsystems.tshop.services.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/order")
@SessionAttributes({"order", "productsInCart"})
public class OrderController {

    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    private final OrderService orderService;

    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {

        this.orderService = orderService;
        this.productService = productService;
    }

    @ModelAttribute("productsInCart")
    public List<Product> getProducts() {

        return new ArrayList<>();
    }

    @ModelAttribute("orderStatusOptions")
    public List<OrderStatus> getStatusOptions() {

        return Arrays.asList(OrderStatus.values());

    }

    @ModelAttribute("order")
    public Order getOrder() {

        return new Order();
    }

    @GetMapping("/cart/clear")
    public void clearCart(@ModelAttribute("productsInCart") List<Product> productsInCart) {

        for (int i = 0; i < productsInCart.size(); i++) {
            orderService.removeProductFromCart(productsInCart, productsInCart.get(i).getProductId());
            LOGGER.info("cart has been cleared: " + productsInCart);
        }
    }

    @ResponseBody
    @PostMapping("/cart/add")
    public Product addToCart(@RequestBody Long productId,
                             @ModelAttribute("productsInCart") List<Product> productsInCart) {

        productsInCart = orderService.addProductToCart(productsInCart, productId);
        LOGGER.info("product with id: " + productId +
                " has been added to cart. Products in cart :" + productsInCart);

        return productService.findProductById(productId);
    }

    @ResponseBody
    @PostMapping("/cart/remove")
    public int removeFromCart(@RequestBody Long productId,
                              @ModelAttribute("productsInCart") List<Product> productsInCart) {

        productsInCart = orderService.removeProductFromCart(productsInCart, productId);
        return productsInCart.size();
    }

    @ResponseBody
    @GetMapping("/cart/count")
    public int countProducts(@ModelAttribute("productsInCart") List<Product> productsInCart) {

        LOGGER.info(" Products in cart : "
                + productsInCart);
        return productsInCart.size();
    }

    @ResponseBody
    @GetMapping("/cart/total")
    public BigDecimal getTotal(@ModelAttribute("productsInCart") List<Product> productsInCart) {

        LOGGER.info("total sum of products in cart = " + this.productService.getTotal(productsInCart));
        return this.productService.getTotal(productsInCart);
    }

    @GetMapping("/history")
    public String repeatOrder(Model model) {


        model.addAttribute("orders", this.orderService.findUserOrders());
        return "order_history";

    }

    @GetMapping("/{orderId}")
    public String findOrder(@ModelAttribute("order") Order order,
                            Model model,
                            @PathVariable Long orderId) {

        try {
            model.addAttribute("order", this.orderService.findOrder(orderId));
            LOGGER.info("order: " + this.orderService.findOrder(orderId) + " was found with ID " + orderId);
        } catch (Exception e) {
            model.addAttribute("orderId", orderId);
            return "order_not_found";
        }
        return "order";
    }

    @GetMapping("/place")
    public String orderPlace(@ModelAttribute("order") Order order,
                             Model model,
                             @ModelAttribute("productsInCart") List<Product> productsInCart
    ) {

        List<String> paymentOptions = new ArrayList<>(Arrays.asList("cash", "online"));
        model.addAttribute("paymentOptions", paymentOptions);
        List<String> deliveryOptions = new ArrayList<>(Arrays.asList("pickup", "delivery"));
        model.addAttribute("deliveryOptions", deliveryOptions);

        model.addAttribute("cartItems", productsInCart.size());

        this.orderService.createNewOrder(order);
        LOGGER.info("order " + order + " was created");
        return "order_place";
    }

    @GetMapping("/edit")
    public String updateOrder(
            Model model
    ) {

        List<OrderStatus> orderStatusOptions = Arrays.asList(OrderStatus.values());
        List<PaymentStatus> paymentStatusOptions = Arrays.asList(PaymentStatus.values());
        model.addAttribute("paymentStatusOptions", paymentStatusOptions);
        model.addAttribute("orderStatusOptions", orderStatusOptions);
        return "order_update";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute("order") Order order) {

        try {
            orderService.updateOrder(order);
            return "redirect:/order/edit?orderUpdated=true";
        } catch (RuntimeException e) {
            return "redirect:/order/edit?orderUpdated=false";
        }

    }

    @PostMapping("/review")
    public String reviewOrder(@ModelAttribute("order") Order order,
                              @ModelAttribute("productsInCart") List<Product> productsInCart
    ) {

        return "order_review";
    }

    @GetMapping("/save")
    public String saveOrder(SessionStatus sessionStatus,
                            @ModelAttribute("order") Order order,
                            @ModelAttribute("productsInCart") List<Product> productsInCart,
                            @ModelAttribute("card") Card card) {

        if (order.getPaymentMethod().equals("cash")) {
            this.orderService.saveOrder(order, productsInCart);
            LOGGER.info("order " + order + " was saved");
            sessionStatus.setComplete();
        } else {

            return "order_payment";
        }

        return "redirect:/product/all?placementProblem=false";
    }

    @PostMapping("/pay")
    public String payWithCard(@ModelAttribute("card") Card card,
                              @ModelAttribute("order") Order order,
                              @ModelAttribute("productsInCart") List<Product> productsInCart,
                              SessionStatus sessionStatus) {

        try {
            this.orderService.payWithCard(card, order, productsInCart);
            sessionStatus.setComplete();
        } catch (RuntimeException e) {
            return "redirect:/order/save?paymentProblem=true";
        }

        return "redirect:/product/all?paymentProblem=false";

    }


    @GetMapping("/all")
    public String getOrders() {

        return "orders";
    }

    @ResponseBody
    @GetMapping("/find/all")
    public List<Order> getAllOrders() {

        return orderService.getOrders();
    }

    @ResponseBody
    @GetMapping("/find/all/dates")
    public List<Order> getAllOrdersByDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstDay,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate lastDay) {

        return orderService.getOrdersWithDatesBetween(firstDay, lastDay);
    }

    @ResponseBody
    @PostMapping("/products")
    public List<Product> getProductsByOrder(@RequestBody Long orderId) {

        LOGGER.info("products for order with id: " + orderId + this.orderService.getProductsByOrderId(orderId) + "have been sent to browser");
        return this.orderService.getProductsByOrderId(orderId);
    }

    @ResponseBody
    @GetMapping("/stats/profit")
    public List<Profit> getProfitStatistics(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstDay,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate lastDay,
            @RequestParam(required = false) String period) {

        return orderService.getProfit(firstDay, lastDay, period);
    }

    @GetMapping("/profit")
    public String getProfit() {

        return "profit";
    }


}
