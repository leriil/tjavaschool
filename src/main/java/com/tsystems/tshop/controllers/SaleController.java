package com.tsystems.tshop.controllers;

import com.tsystems.tshop.domain.Address;
import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.domain.Sale;
import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.services.AddressService;
import com.tsystems.tshop.services.ProductService;
import com.tsystems.tshop.services.SaleService;
import com.tsystems.tshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
@Controller
@RequestMapping("/order")
@SessionAttributes({"order","productsInCart","address","user"})

public class SaleController {

    private static final Logger log=Logger.getLogger("Log");

@Autowired
    AddressService addressService;

@Autowired
    UserService userService;

@Autowired
    SaleService saleService;
@Autowired
    ProductService productService;

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
public Sale getSale(){
    return new Sale();
}

    @ResponseBody
    @RequestMapping(value = "/cart/add",method = RequestMethod.POST)
    public Long addToCart(@RequestBody Long productId,
                          @ModelAttribute ("productsInCart") List <Product> productsInCart){
        productsInCart.add(this.productService.findOne(productId));
        System.out.println("product with id: "+productId+" has been added to cart");
        System.out.println("products in cart : "+productsInCart);
        return productId;
    }
    @ResponseBody
    @RequestMapping(value = "/cart/remove",method = RequestMethod.POST)
    public Long removeFromCart(@RequestBody Long productId,
                          @ModelAttribute ("productsInCart") List <Product> productsInCart){
        Product productForRemoval=this.productService.findOne(productId);
        productsInCart.removeIf(product -> product.equals(productForRemoval));
            log.log(Level.WARNING," Products in cart after removal left : "
                    +productsInCart);
        return productId;
    }

@RequestMapping("/history")
public String repeatOrder(Model model){
    String login=SecurityContextHolder.getContext().getAuthentication().getName();
    model.addAttribute("orders",this.saleService.findUserOrders(login));
    System.out.println("from /history   "+this.saleService.findUserOrders(login));

    return "order_history";
}

    @RequestMapping(value = "/{saleId}")
    public String findProduct(@ModelAttribute("order") Sale sale, Model model, @PathVariable Long saleId){
        try{model.addAttribute("order",this.saleService.findSale(saleId));
            System.out.println("from /{saleId} "+this.saleService.findSale(saleId));
        }catch (RuntimeException e){
            return "order_not_found";
        }
        return "order";
    }

    @RequestMapping(value = "/place")
    public String saveSale( @ModelAttribute("order")Sale sale,
//                            @ModelAttribute("productsInCart")List<Product> productsInCart,
                            @ModelAttribute("address")Address address,
                            @ModelAttribute("user") User user
    ) {
//        String login=SecurityContextHolder.getContext().getAuthentication().getName();
        user=this.userService.getUser();
        sale.setUser(user);
        Address currentAddress=this.addressService.userCurrentAddress(user);
       if(currentAddress!=null){
           sale.setAddress(currentAddress);
       }

//        this.saleService.saveSale(sale);
//        System.out.println(sale);
        return "order_place";
    }

    @RequestMapping(value = "/review",method = RequestMethod.POST)
    public String reviewOrder(@ModelAttribute("order")Sale sale,
                              @ModelAttribute("productsInCart")List<Product> productsInCart,
                              @ModelAttribute("address")Address address,
                              @ModelAttribute("user") User user
    ) {

        if(!sale.getAddress().equals(this.addressService.userCurrentAddress(sale.getUser()))){
            sale.getAddress().setAddressId(null);
        }
        return "order_review";
    }

    @RequestMapping("/save")
    public String saveOrder(@ModelAttribute("order")Sale sale,
                            @ModelAttribute("productsInCart")List<Product> productsInCart,
//                            @ModelAttribute("address")Address address,
//                            @ModelAttribute("user") User user,
                            SessionStatus sessionStatus){
        //start here with bedug, find out why address and user are null
//        sale.getAddress().setAddressId(null);
//            this.addressService.saveAddress(sale.getAddress());



//        sale.setAddress(address);
        sale.setProducts(productsInCart);
//        sale.setDeliveryMethod(sale.getDeliveryMethod());
//        sale.setPaymentMethod(sale.getPaymentMethod());
        sale.setOrderStatus("waiting for approval");
        sale.setPaymentStatus("not paid");
        this.saleService.saveSale(sale);
        sessionStatus.setComplete();
        return "redirect:/product/all";
    }

    @RequestMapping(value = "/repeat/save")
    public String saveSale(@ModelAttribute("order")Sale sale, SessionStatus status){
    sale.setSaleId(null);
        System.out.println("from /repeat/save   "+sale);
        this.saleService.saveSale(sale);
        System.out.println("after saving a sale "+sale);
        status.setComplete();
        return "redirect:/product/all";
    }


//    @RequestMapping(value = "/")
//    public String confirmOrder(){
//        return "order_confirm";
//    }

}
