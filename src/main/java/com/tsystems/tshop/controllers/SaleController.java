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
    public int addToCart(@RequestBody Long productId,
                          @ModelAttribute ("productsInCart") List <Product> productsInCart){
        productsInCart.add(this.productService.findOne(productId));
        log.log(Level.WARNING,"product with id: "+productId+
                " has been added to cart. Products in cart : \"+productsInCart");
        return productsInCart.size();
    }
    @ResponseBody
    @RequestMapping(value = "/cart/remove",method = RequestMethod.POST)
    public int removeFromCart(@RequestBody Long productId,
                          @ModelAttribute ("productsInCart") List <Product> productsInCart){
        Product productForRemoval=this.productService.findOne(productId);
        productsInCart.removeIf(product -> product.equals(productForRemoval));
            log.log(Level.WARNING," Products in cart after removal left : "
                    +productsInCart);
        return productsInCart.size();
    }

    @ResponseBody
    @RequestMapping(value = "/cart/count",method = RequestMethod.GET)
    public int countProducts(@ModelAttribute ("productsInCart") List <Product> productsInCart){
        log.log(Level.WARNING," Products in cart : "
                +productsInCart);
        return productsInCart.size();
    }

    @RequestMapping("/history")
    public String repeatOrder(Model model){
    String login=SecurityContextHolder.getContext().getAuthentication().getName();
    model.addAttribute("orders",this.saleService.findUserOrders(login));
    log.log(Level.WARNING,"from /history   "+this.saleService.findUserOrders(login) );
    return "order_history";
}

    @RequestMapping(value = "/{saleId}")
    public String findSale(@ModelAttribute("order") Sale sale,
                              Model model, @PathVariable Long saleId){
        try{model.addAttribute("order",this.saleService.findSale(saleId));
            log.log(Level.WARNING,"from /{saleId} "+this.saleService.findSale(saleId) );
        }catch (RuntimeException e){
            return "order_not_found";
        }
        return "order";
    }

    @RequestMapping(value = "/place")
    public String orderPlace( @ModelAttribute("order")Sale sale,
                            @ModelAttribute("address")Address address,
                            @ModelAttribute("user") User user
    ) {
        user=this.userService.getUser();
        sale.setUser(user);
        Address currentAddress=this.addressService.userCurrentAddress(user);
       if(currentAddress!=null){
           sale.setAddress(currentAddress);
       }
        return "order_place";
    }

    @RequestMapping(value = "/review",method = RequestMethod.POST)
    public String reviewOrder(@ModelAttribute("order")Sale sale,
                              @ModelAttribute("productsInCart")List<Product> productsInCart
    ) {
        return "order_review";
    }

    @RequestMapping("/save")
    public String saveOrder(@ModelAttribute("order")Sale sale,
                            @ModelAttribute("productsInCart")List<Product> productsInCart,
                            SessionStatus sessionStatus){
        this.addressService.save(sale.getAddress());
        sale.getUser().getAddresses().add(sale.getAddress());
        this.userService.saveUser(sale.getUser());
        sale.setProducts(productsInCart);
        sale.setOrderStatus("waiting for approval");
        sale.setPaymentStatus("not paid");
        this.saleService.saveSale(sale);
        sessionStatus.setComplete();
        return "redirect:/product/all";
    }

    @RequestMapping(value = "/repeat/save")
    public String saveSale(@ModelAttribute("order")Sale sale, SessionStatus status){
        sale.setSaleId(null);
        log.log(Level.WARNING,"from /repeat/save   "+sale);
        this.saleService.saveSale(sale);
        log.log(Level.WARNING,"after saving a sale "+sale);
        status.setComplete();
        return "redirect:/product/all";
    }

}
