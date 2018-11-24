package com.tsystems.tshop.controllers;


import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.domain.ProductTop;
import com.tsystems.tshop.services.CategoryService;
import com.tsystems.tshop.services.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@SessionAttributes({"product"})
@RequestMapping("/product")
@Api(value = "product",description = "managing products",tags = "products")
public class ProductController {

    private static final Logger log = Logger.getLogger("LOGGER");

    ProductService productService;
    CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService,
                             CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("product")
    public Product getProduct() {

        return new Product();
    }

    @ResponseBody
    @RequestMapping(value = "/category/add", method = RequestMethod.POST)
    public void setProductCategory(@RequestBody String categoryName,
                                   @ModelAttribute Product product){
        product.setCategory(categoryService.getCategoryByName(categoryName));

    }

//    @ModelAttribute("categoryOptions")
//    public List<ProductCategory> getCategories() {
//        return new LinkedList<>(Arrays.asList(ProductCategory.values()));
//    }

    @ResponseBody
    @RequestMapping("/categories")
    public List<com.tsystems.tshop.domain.ProductCategory> getCategories(){
        return categoryService.getCategories();
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
        try {
            this.productService.save(product);
            status.setComplete();
            return "redirect:/product/add?addProductProblem=false";
        } catch (RuntimeException e) {
            return "redirect:/product/add?addProductProblem=true";
        }
    }

    //TODO: make a view for this method
    @ResponseBody
    @RequestMapping(value = "/find/top")
    public List<ProductTop> topProducts(
            @RequestParam(value = "order", required = false) String order
    ) {
        return productService.getTopProducts(order);

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
    @RequestMapping(value = "/find/{name}")
    public List<Product> findProductsByName(@PathVariable String name){
        return this.productService.findProductByName(name);
    }

    @RequestMapping("/stats")
    public String getStats(){
        return "statistics";
    }

//    @ExceptionHandler(NullPointerException.class)
//    public String handleError(){
//        return "controller_error";
//    }
}

