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
import com.tsystems.tshop.domain.Sale;
import com.tsystems.tshop.enums.ProductCategory;
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
@SessionAttributes({"product","sale","products"})
@RequestMapping("/product")
public class ProductController {
    @Autowired
	ProductService productService;

    @Autowired
    SaleService saleService;

    @Autowired
    UserService userService;

    private static final Logger log=Logger.getLogger("Log");

    List<Product> products=new ArrayList<>();



    //	@GetMapping("/{id}")
//	public Product getProduct(@PathVariable Long id) {
//		return productService.getProductById(id);
//	}
    @ModelAttribute("product")
    public Product getProduct() {
    log.info("adding a product to the model");
    return new Product();
}

    @ModelAttribute("sale")
    public Sale getSale(){
        return new Sale();
    }


    @ModelAttribute("categoryOptions")
    public List<ProductCategory> getCategories(){
        return new LinkedList<>(Arrays.asList(ProductCategory.values()));
    }

    @RequestMapping("/review")
    public String reviewProduct(@ModelAttribute Product product){
        return "product_review";
    }


    @RequestMapping (value = "/add",method=RequestMethod.GET)
    public String addProduct(Model model){
       return "product_add";
    }

    @RequestMapping (value = "/save")
    public String saveProduct(@ModelAttribute Product product,SessionStatus status) {
        this.productService.save(product);
        status.setComplete();
        return "redirect:/product/add";
    }
    @RequestMapping(value = "/find/top")
    public String topProducts(){
        return "product_top";
    }

    @RequestMapping("/all")
    public String findAll(Model model){
        model.addAttribute("products",this.productService.findProducts());
        return "products";
    }

    @RequestMapping(value = "/{productId}")
    public String findProduct(Model model, @PathVariable Long productId){
        try{model.addAttribute("product",this.productService.findOne(productId));
        }catch (RuntimeException e){
            return "product_not_found";
        }
        return "product";
    }

//    @ResponseBody
//    @RequestMapping(value = "/addtocart")
//    public String placeOrder(@ModelAttribute Sale sale, @ModelAttribute Product product) {
//        System.out.println(product.toString()+"this is the product id");
//        products.add(product);
//        sale.setProducts(products);
//        String username=SecurityContextHolder.getContext().getAuthentication().getName();
//        sale.setUser(this.userService.getUser(username));
//        sale.setDeliveryMethod("delivery");
//        sale.setPaymentMethod("cash");
//        this.saleService.saveSale(sale);
//        return "order_place";
//    }
@ResponseBody
    @RequestMapping(value = "/addToCart",method = RequestMethod.POST)
    public Long addToCart(@RequestBody Long productId){
       // Long Id=Long.parseLong(productId);
        products.add(this.productService.findOne(productId));
        System.out.println(productId);
    System.out.println(products);
//        Long id=Long.parseLong(productId);
        return productId;
    }

//    @RequestMapping (value = "/add",method=RequestMethod.POST)
//    public String saveProduct(@ModelAttribute Product product, SessionStatus status){
//    this.productService.save(product);
//    status.setComplete();
//        return "redirect:/product/add";
//    }
}
