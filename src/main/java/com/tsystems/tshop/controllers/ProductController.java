package com.tsystems.tshop.controllers;


import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.domain.ProductTop;
import com.tsystems.tshop.enums.ProductCategory;
import com.tsystems.tshop.services.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@SessionAttributes({"product"})
@RequestMapping("/product")
@Api(value = "product",description = "managing products",tags = "products")
public class ProductController {

    private static final Logger log = Logger.getLogger("LOGGER");

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("product")
    public Product getProduct() {

        return new Product();
    }

    @ModelAttribute("categoryOptions")
    public List<ProductCategory> getCategories() {
        return new LinkedList<>(Arrays.asList(ProductCategory.values()));
    }

    @ResponseBody
    @RequestMapping("/sort")
    public List<Product> sortProducts(
            @RequestParam(value = "column", required = false) String column,
            @RequestParam(value = "order", required = false) String order
    )
    {
        return this.productService.sortProducts(column, order);
    }

    @RequestMapping("/review")
    public String reviewProduct(@Valid @ModelAttribute Product product,
                                Errors errors) {
        if(!errors.hasErrors())
        {return "product_review";}

        return "product_add";
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

    //TODO: make a view for this method
    @ResponseBody
    @RequestMapping(value = "/find/top")
    public List<ProductTop> topProducts(Model model) {
        List<ProductTop> topProducts =  this.productService.getTopProducts();
        model.addAttribute("top", topProducts);
        return topProducts;
    }

    //TODO: maybe return the List of sorted products in a separate method?
    @RequestMapping("/all")
    public String findAll(Model model,
                          @RequestParam(value = "sortingOption", required = false) String sortingOption,
                          @RequestParam(value = "sortingOrder", required = false) String sortingOrder) {
        if((sortingOption!=null)&&(sortingOrder!=null)){
//
            model.addAttribute("products",this.productService.getSortedProducts(sortingOption,sortingOrder));
        }
        else {model.addAttribute("products", this.productService.findProducts());}
        return "products";
    }

    @RequestMapping(value = "/{productId}")
    public String findProduct(Model model,
                              @PathVariable Long productId) {
        try {
            model.addAttribute("product", this.productService.findProductById(productId));
        } catch (RuntimeException e) {
            return "product_not_found";
        }
        return "product";
    }

    //TODO: this method will be used by sales stuff to search for a particular product
    @ResponseBody
    @RequestMapping(value = "/{name}")
    public List<Product> findProductsByName(@PathVariable String name){
        return this.productService.findProductByName(name);
    }

//    @ExceptionHandler(NullPointerException.class)
//    public String handleError(){
//        return "controller_error";
//    }
}

