package com.tsystems.tshop.controllers;

import com.tsystems.tshop.domain.*;
import com.tsystems.tshop.services.OrderService;
import com.tsystems.tshop.services.ProductService;
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
import java.util.logging.Level;
import java.util.logging.Logger;
@Controller
@RequestMapping("/order")
@SessionAttributes({"order","productsInCart","address","user"})
public class OrderController {

    private static final Logger log=Logger.getLogger("LOGGER");

    OrderService orderService;

    ProductService productService;

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

        productsInCart.add(this.productService.findOne(productId));
        log.info("product with id: "+productId+
                " has been added to cart. Products in cart : \"+productsInCart");

        return productsInCart.size();
    }
    //TODO:it probably shouldn't return anything, or at least just a product
    @ResponseBody
    @RequestMapping(value = "/cart/remove",method = RequestMethod.POST)
    public int removeFromCart(@RequestBody Long productId,
                          @ModelAttribute ("productsInCart") List <Product> productsInCart){
//        Product productForRemoval=this.productService.findOne(productId);
        //TODO: only one instance should be removed, not all of them
//        productsInCart.removeIf(product -> product.equals(productForRemoval));
        Optional<Product> product=productsInCart.stream().filter(p -> p.getProductId()==productId).findAny();
        if(product.isPresent()){
            productsInCart.remove(product.get());
        }
//            log.info(" Products in cart after removal left : "
//                    +productsInCart);
//            productsInCart.remove(productForRemoval);
        return productsInCart.size();
    }

    @ResponseBody
    @RequestMapping(value = "/cart/count",method = RequestMethod.GET)
    public int countProducts(@ModelAttribute ("productsInCart") List <Product> productsInCart){
        log.info(" Products in cart : "
                +productsInCart);
        return productsInCart.size();
    }

    @ResponseBody
    @RequestMapping(value = "/cart/total",method = RequestMethod.GET)
    public BigDecimal getTotal(@ModelAttribute ("productsInCart") List <Product> productsInCart){

        return this.productService.getTotal(productsInCart);
    }

    @RequestMapping("/history")
    public String repeatOrder(Model model){
    String login=SecurityContextHolder.getContext().getAuthentication().getName();
    model.addAttribute("orders",this.orderService.findUserOrders(login));
    log.log(Level.WARNING,"from /history   "+this.orderService.findUserOrders(login) );
    return "order_history";
}

    @RequestMapping(value = "/{orderId}")
    public String findOrder(@ModelAttribute("order") Order order,
                              Model model, @PathVariable Long orderId){
        try{model.addAttribute("order",this.orderService.findOrder(orderId));
            log.log(Level.WARNING,"from /{orderId} "+this.orderService.findOrder(orderId) );
        }catch (RuntimeException e){
            return "order_not_found";
        }
        return "order";
    }
//TODO: get rid of address and user, they are null after creating a new order
    @RequestMapping(value = "/place")
    public String orderPlace( @ModelAttribute("order")Order order,
                              Model model
    ) {
        List<String> paymentOptions=new ArrayList<>(Arrays.asList("cash","online"));
        model.addAttribute("paymentOptions",paymentOptions);
        List<String>deliveryOptions=new ArrayList<>(Arrays.asList("pickup", "delivery"));
        model.addAttribute("deliveryOptions", deliveryOptions);
        this.orderService.createNewOrder(order);
        return "order_place";
    }

    @RequestMapping(value = "/review",method = RequestMethod.POST)
    public String reviewOrder(@ModelAttribute("order")Order order,
                              @ModelAttribute("productsInCart")List<Product> productsInCart
    ) {
        return "order_review";
    }

    @RequestMapping("/save")
    public String saveOrder(Model model,
                            @ModelAttribute("order")Order order,
                            @ModelAttribute("productsInCart")List<Product> productsInCart,
                            SessionStatus sessionStatus,
                            @ModelAttribute("card")Card card){
        if(order.getPaymentMethod().equals("cash")){
            this.orderService.saveOrder(order,productsInCart);
            sessionStatus.setComplete();}
        else{

            return "order_payment";
        }

        return "redirect:/product/all";
    }
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String payWithCard(Model model,
                              @ModelAttribute("card")Card card,
                              @ModelAttribute("order")Order order,
                              @ModelAttribute("productsInCart")List<Product> productsInCart,
                              SessionStatus sessionStatus){
        this.orderService.payWithCard(card,order,productsInCart);
        sessionStatus.setComplete();
        return "redirect:/product/all";

    }

    @RequestMapping(value = "/repeat/save")
    public String saveOrder(@ModelAttribute("order")Order order, SessionStatus status){
        order.setOrderId(null);
        log.log(Level.WARNING,"from /repeat/save   "+ order);
        this.orderService.saveOrder(order);
        log.log(Level.WARNING,"after saving a order "+ order);
        status.setComplete();
        return "redirect:/product/all";
    }

}
