package com.tsystems.tshop.controllers;

import com.tsystems.tshop.domain.*;
import com.tsystems.tshop.services.OrderService;
import com.tsystems.tshop.services.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/order")
@SessionAttributes({"order","productsInCart","address","user"})
public class OrderController {

    private Logger LOGGER = LogManager.getLogger(OrderController.class);

    private OrderService orderService;

    private ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @ModelAttribute("productsInCart")
    public List<Product> getProducts(){
        return new ArrayList<>();
    }

    @ModelAttribute("address")
    public Address getAddress(){
        return new Address();
    }

    @ModelAttribute("user")
    public User getUser(){
        return new User();
    }

    @ModelAttribute("order")
    public Order getOrder(){
    return new Order();
}

    @ResponseBody
    @RequestMapping(value = "/cart/add",method = RequestMethod.POST)
    public int addToCart(@RequestBody Long productId,
                          @ModelAttribute ("productsInCart") List <Product> productsInCart){

        productsInCart.add(this.productService.findProductById(productId));
        LOGGER.info("product with id: "+productId+
                " has been added to cart. Products in cart :" +productsInCart);

        return productsInCart.size();
    }
    //TODO:it probably shouldn't return anything, or at least just a product
    @ResponseBody
    @RequestMapping(value = "/cart/remove",method = RequestMethod.POST)
    public int removeFromCart(@RequestBody Long productId,
                          @ModelAttribute ("productsInCart") List <Product> productsInCart){

        Optional<Product> product=productsInCart.stream().filter(p -> p.getProductId().equals(productId)).findAny();
        if(product.isPresent()){
            productsInCart.remove(product.get());
            LOGGER.warn("product "+product+" has been removed from cart");
        }
        return productsInCart.size();
    }

    @ResponseBody
    @RequestMapping(value = "/cart/count",method = RequestMethod.GET)
    public int countProducts(@ModelAttribute ("productsInCart") List <Product> productsInCart){
        LOGGER.info(" Products in cart : "
                +productsInCart);
        return productsInCart.size();
    }

    @ResponseBody
    @RequestMapping(value = "/cart/total",method = RequestMethod.GET)
    public BigDecimal getTotal(@ModelAttribute ("productsInCart") List <Product> productsInCart){

        LOGGER.info("total sum of products in cart = "+this.productService.getTotal(productsInCart));
        return this.productService.getTotal(productsInCart);
    }

    @RequestMapping("/history")
    public String repeatOrder(Model model){
    String login=SecurityContextHolder.getContext().getAuthentication().getName();
    model.addAttribute("orders",this.orderService.findUserOrders(login));

    LOGGER.info("users orders are: "+this.orderService.findUserOrders(login));
    return "order_history";
}

    @RequestMapping(value = "/{orderId}")
    public String findOrder(@ModelAttribute("order") Order order,
                              Model model, @PathVariable Long orderId){
        try{model.addAttribute("order",this.orderService.findOrder(orderId));
            LOGGER.info("order: "+this.orderService.findOrder(orderId)+" was found with ID "+orderId );
        }catch (RuntimeException e){
            return "order_not_found";
        }
        return "order";
    }

    @RequestMapping(value = "/place")
    public String orderPlace( @ModelAttribute("order")Order order,
                              Model model
//                              @ModelAttribute("productsInCart")List <Product> productsInCart
    ) {
        List<String> paymentOptions=new ArrayList<>(Arrays.asList("cash","online"));
        model.addAttribute("paymentOptions",paymentOptions);
        List<String>deliveryOptions=new ArrayList<>(Arrays.asList("pickup", "delivery"));
        model.addAttribute("deliveryOptions", deliveryOptions);

        this.orderService.createNewOrder(order);
        LOGGER.info("order "+order+" was created");
        return "order_place";
    }

    @RequestMapping(value = "/review",method = RequestMethod.POST)
    public String reviewOrder(@ModelAttribute("order")Order order,
                              @ModelAttribute("productsInCart")List<Product> productsInCart
    ) {
        return "order_review";
    }

    @RequestMapping("/save")
    public String saveOrder(SessionStatus sessionStatus,
                            @ModelAttribute("order")Order order,
                            @ModelAttribute("productsInCart")List<Product> productsInCart,
                            @ModelAttribute("card")Card card){
        if(order.getPaymentMethod().equals("cash")){
            this.orderService.saveOrder(order,productsInCart);
            LOGGER.info("order "+order+" was saved");
            sessionStatus.setComplete();}
        else{

            return "order_payment";
        }

        return "redirect:/product/all?placementProblem=false";
    }
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String payWithCard(Model model,
                              @ModelAttribute("card")Card card,
                              @ModelAttribute("order")Order order,
                              @ModelAttribute("productsInCart")List<Product> productsInCart,
                              SessionStatus sessionStatus){
        try{this.orderService.payWithCard(card,order,productsInCart);
            sessionStatus.setComplete();}
            catch (RuntimeException e){
            return "redirect:/order/save?paymentProblem=true";
            }

        return "redirect:/product/all?paymentProblem=false";

    }

    @RequestMapping(value = "/repeat/save")
    public String saveOrder(@ModelAttribute("order")Order order,
                            SessionStatus status){
        order.setOrderId(null);
        LOGGER.info("from /repeat/save   "+ order);
        this.orderService.saveOrder(order);
        LOGGER.info("after saving a order "+ order);
        status.setComplete();
        return "redirect:/product/all";
    }

    @ResponseBody
    @RequestMapping(value="/products",method = RequestMethod.POST)
    public List<Product> getProductsByOrder(@RequestBody Long orderId){

        LOGGER.info("products for order with id: "+orderId+ this.orderService.getProductsByOrderId(orderId)+ "have been sent to browser");
        return this.orderService.getProductsByOrderId(orderId);
    }

}
