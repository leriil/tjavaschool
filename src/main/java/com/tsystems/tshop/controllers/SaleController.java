package com.tsystems.tshop.controllers;

import com.tsystems.tshop.domain.Sale;
import com.tsystems.tshop.services.ProductService;
import com.tsystems.tshop.services.SaleService;
import com.tsystems.tshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.logging.Logger;
@Controller
@RequestMapping("/order")
@SessionAttributes({"order"})

public class SaleController {

    private static final Logger log=Logger.getLogger("Log");


@Autowired
    UserService userService;

@Autowired
    SaleService saleService;
@Autowired
    ProductService productService;

@ModelAttribute("order")
public Sale getSale(){
    return new Sale();
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

    @RequestMapping(value = "/repeat/save")
    public String saveSale(@ModelAttribute("order")Sale sale, SessionStatus status){
    sale.setSaleId(null);
        System.out.println("from /repeat/save   "+sale);
        this.saleService.saveSale(sale);
        System.out.println("after saving a sale "+sale);
        status.setComplete();
        return "redirect:/product/all";
    }

    @RequestMapping(value = "/")
    public String confirmOrder(){
        return "order_confirm";
    }

}
