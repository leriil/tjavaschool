package com.tsystems.tshop.controllers;

import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.services.ProductService;
import com.tsystems.tshop.services.SaleService;
import com.tsystems.tshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@Controller
@RequestMapping("/order")
@SessionAttributes({"order","product","products","productCounter"})

public class SaleController {

    private static final Logger log=Logger.getLogger("Log");

List<Product> products=new ArrayList<>();
public static int productCounter;

@Autowired
    UserService userService;

@Autowired
    SaleService saleService;
@Autowired
    ProductService productService;

@ModelAttribute("product")
public Product getProduct(){
    return new Product();
}



    @RequestMapping(value = "/")
    public String confirmOrder(){
        return "order_confirm";
    }

}
