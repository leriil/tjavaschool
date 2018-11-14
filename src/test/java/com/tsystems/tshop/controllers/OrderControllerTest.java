//package com.tsystems.tshop.controllers;
//
//import com.tsystems.tshop.domain.Product;
//import com.tsystems.tshop.enums.ProductCategory;
//import com.tsystems.tshop.repositories.OrderRepository;
//import com.tsystems.tshop.services.OrderService;
//import com.tsystems.tshop.services.ProductService;
//import org.junit.jupiter.api.*;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//class OrderControllerTest {
//
//    public static OrderController controller;
//    public static Long id;
//    public static Product one;
//    public static Product two;
//
//    @BeforeAll
//    static void init(){
//        OrderService orderService=new OrderService();
//        ProductService productService = new ProductService();
//        controller=new OrderController(orderService,productService);
//
//        id=1L;
//
//        one=new Product();
//        one.setProductId(1L);
//        one.setCategory(ProductCategory.DEVICE);
//        one.setName("phone");
//
//        two=new Product();
//        two.setProductId(2L);
//        two.setCategory(ProductCategory.DEVICE);
//        two.setName("ebook");
//
//    }
//
//    @DisplayName("remove from cart")
//    @Test
//    void removeFromCart() {
//
//        List<Product> productsInCart=new ArrayList<>(Arrays.asList(one,two));
//        List<Product> productsInCartLeft=new ArrayList<>(Arrays.asList(two));
//
//        Assertions.assertEquals(productsInCartLeft.size(),controller.removeFromCart(id,productsInCart));
//    }
//
//    @Test
//    @Disabled("notRemoveFromCart() not implemented yet")
//    void notRemoveFromCart(){
//
//        List<Product> productsInCart=new ArrayList<>(Arrays.asList(one,two));
//        List<Product> productsInCartLeft=new ArrayList<>(Arrays.asList(two));
//
//        Assertions.assertFalse(controller.removeFromCart(3L,productsInCart)==productsInCartLeft.size());
//
//    }
//}