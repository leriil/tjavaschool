package com.tsystems.tshop.controllers;


import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.domain.ProductTop;
import com.tsystems.tshop.services.CategoryService;
import com.tsystems.tshop.services.ProductService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes({"product"})
@RequestMapping("/product")
@Api(value = "product",description = "managing products",tags = "products")
public class ProductController {

    private static final Logger LOGGER = LogManager.getLogger(ProductController.class);

    private final ProductService productService;
    private final CategoryService categoryService;

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
        if (!errors.hasErrors()) {
            return "product_review";
        }
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


    @ResponseBody
    @RequestMapping(value = "/find/top")
    public List<ProductTop> topProducts(
            @RequestParam(value = "order", required = false) String order
    ) {
        List<ProductTop> productTopList = productService.getTopProducts(order);

        LOGGER.info("productTop from controller" + productTopList);
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
                              @PathVariable Long productId,
                              SessionStatus status) {

            model.addAttribute("product", this.productService.findProductById(productId));
        status.setComplete();
        return "product";
    }

    @RequestMapping(value = "/find")
    public String findProductsByName(Model model, @RequestParam("search") String name) {
        List<Product> productSearch = productService.findProductByName(name);
        model.addAttribute("itemsNotFound", productSearch.isEmpty());
        model.addAttribute("productSearch", productSearch);
        return "products_search";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleUpload(@RequestParam("product_file") MultipartFile file) {
        try {
            productService.parseFile(file);
            return "redirect:/product/file/upload?fileUploaded=true";
        } catch (Exception e) {
            return "redirect:/product/file/upload?fileUploaded=false";
        }
    }

    @GetMapping("/file/upload")
    public String showUploadPage(
    ) {
        return "file_upload";
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

