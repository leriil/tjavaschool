package com.tsystems.tshop.controllers;//package com.tsystems.tshop.controllers;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.tsystems.tshop.domain.Product;
//import com.tsystems.tshop.services.ProductService;
//

import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.enums.ProductCategory;
import com.tsystems.tshop.repositories.UserRepository;
import com.tsystems.tshop.services.AddressService;
import com.tsystems.tshop.services.ProductService;
import com.tsystems.tshop.services.SaleService;
import com.tsystems.tshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@SessionAttributes({"product"})
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    SaleService saleService;

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @Autowired
    UserRepository userRepository;

    private static final Logger log = Logger.getLogger("Log");

    @ModelAttribute("product")
    public Product getProduct() {

        return new Product();
    }

    @ModelAttribute("categoryOptions")
    public List<ProductCategory> getCategories() {
        return new LinkedList<>(Arrays.asList(ProductCategory.values()));
    }

    @RequestMapping("/review")
    public String reviewProduct(@ModelAttribute Product product) {
        return "product_review";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProduct(Model model,
                             @ModelAttribute("product") Product product) {
        return "product_add";
    }

    @RequestMapping(value = "/save")
    public String saveProduct(@ModelAttribute Product product, SessionStatus status) {
        this.productService.save(product);
        status.setComplete();
        return "redirect:/product/add";
    }

    @ResponseBody
    @RequestMapping(value = "/find/top")
    public List<Product> topProducts(Model model) {
        List<Product> topProducts = new ArrayList<>();
        topProducts = this.productService.getTopProducts();
        model.addAttribute("top", topProducts);
        return topProducts;
    }

    @RequestMapping("/all")
    public String findAll(Model model) {
        model.addAttribute("products", this.productService.findProducts());
        return "products";
    }

    @RequestMapping(value = "/{productId}")
    public String findProduct(Model model,
                              @PathVariable Long productId) {
        try {
            model.addAttribute("product", this.productService.findOne(productId));
        } catch (RuntimeException e) {
            return "product_not_found";
        }
        return "product";
    }


//    @ExceptionHandler(NullPointerException.class)
//    public String handleError(){
//        return "controller_error";
//    }
}

