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

import com.tsystems.tshop.domain.Address;
import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.domain.Sale;
import com.tsystems.tshop.domain.User;
import com.tsystems.tshop.enums.ProductCategory;
import com.tsystems.tshop.repositories.UserRepository;
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

import java.util.*;
import java.util.logging.Logger;

@Controller
@SessionAttributes({"product","sale","productsInCart","address","user"})
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

    private static final Logger log=Logger.getLogger("Log");

    @ModelAttribute("user")
    public User getUser(){
    return new User();
}

    @ModelAttribute("productsInCart")
        public Set<Product> getProducts(){
    return new HashSet<>();
}

    @ModelAttribute("address")
     public Address getAddress(){
    return new Address();
}
//    List<Product> products=new ArrayList<>();



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
    public String addProduct(Model model,
                             @ModelAttribute("product")Product product){
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
    @RequestMapping(value = "/placeOrder")
    public String placeOrder(@ModelAttribute("sale") Sale sale,
//                             @ModelAttribute Product product,
                             @ModelAttribute ("productsInCart") List<Product> productsInCart,
                             @ModelAttribute("user") User user,
                             @ModelAttribute("address")Address address) {

//       sale.setProducts(productsInCart);
//        String username=SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println(this.userRepository.queryTwo(username));

//        user=this.userService.getUser(username);
//        if(username!=null){
//            List<Address> userAddresses=this.addressService.getUserAddresses(username);
//            if(userAddresses!=null){
//                System.out.println(userAddresses);
//                address=userAddresses.get(0);
//            }
//        }
//this.addressService.saveAddress(address);
// address.setCountry("Rus");
//        this.addressService.saveAddress(address);
//        sale.setUser(user);
//        sale.setAddress(address);

//        sale.setDeliveryMethod("delivery");
//        sale.setPaymentMethod("cash");
//        this.saleService.saveSale(sale);
        return "order_place";
    }
//    @RequestMapping (value = "/placeOrder")
//    public String orderPlace(
////            @ModelAttribute("address") Address address,
//                             @ModelAttribute("user") User user,
//                             @ModelAttribute Sale sale){
//        String username=SecurityContextHolder.getContext().getAuthentication().getName();
//        sale.setUser(this.userService.getUser(username));
//        System.out.println(this.userService.getUser(username));
//        return "order_place";
//    }
    @ResponseBody
    @RequestMapping(value = "/addToCart",method = RequestMethod.POST)
    public Long addToCart(@RequestBody Long productId,
                          @ModelAttribute ("productsInCart") List <Product> productsInCart){
       // Long Id=Long.parseLong(productId);
        productsInCart.add(this.productService.findOne(productId));
        System.out.println(productId);
    System.out.println(productsInCart);
//        Long id=Long.parseLong(productId);
        return productId;
    }

//    @RequestMapping (value = "/add",method=RequestMethod.POST)
//    public String saveProduct(@ModelAttribute Product product, SessionStatus status){
//    this.productService.save(product);
//    status.setComplete();
//        return "redirect:/product/add";
//    }
//

    @RequestMapping(value = "/order/save",method = RequestMethod.POST)
    public String saveSale( @ModelAttribute("sale")Sale sale){
        System.out.println(sale);
        return "order_review";
    }

    @RequestMapping(value = "/order/confirm")
    public String orderConfirm(@ModelAttribute("productsInCart")Set<Product> productsInCart,
                               @ModelAttribute("sale")Sale sale,
                               @ModelAttribute("address")Address address,
                               SessionStatus status
//                               Session session
    ){this.addressService.saveAddress(address);
        sale.setAddress(address);
        sale.setProducts(productsInCart);
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        sale.setUser(this.userService.getUser(username));
        sale.setDeliveryMethod(sale.getDeliveryMethod());
        sale.setPaymentMethod(sale.getPaymentMethod());
        sale.setOrderStatus("waiting for approval");
        sale.setPaymentStatus("not paid");
        this.saleService.saveSale(sale);
//        session.getSession().remove(productsIncart);
////        status.setComplete();
        return "redirect:/product/all";
    }
}
