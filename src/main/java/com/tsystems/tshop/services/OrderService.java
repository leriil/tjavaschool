package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.*;
import com.tsystems.tshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
@Service
public class OrderService {

    private static final java.util.logging.Logger log = Logger.getLogger("LOGGER");

    OrderRepository orderRepository;

    UserService userService;

    AddressService addressService;

    ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        UserService userService,
                        AddressService addressService,
                        ProductService productService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.addressService = addressService;
        this.productService = productService;
    }

    private final RestTemplate restTemplate=new RestTemplate();

    @Value("http://localhost:8001")
    private String paymentServiceUrl;

    private static final String cards = "/cards";
    private static final String payment = "/payment";

    public void saveOrder(Order order){
        this.orderRepository.save(order);
        log.info( "we have saved::" + order.toString());
    }

   public void payWithCard(Card card,Order order,List<Product>products){

       HttpHeaders headers=new HttpHeaders();
       headers.setContentType(MediaType.MULTIPART_FORM_DATA);

       MultiValueMap<String,String> map = new LinkedMultiValueMap<>();

       map.set("number", String.valueOf(card.getCardNumber()));
       map.set("cvv", String.valueOf(card.getCvv()));
       map.set("userName",String.valueOf(card.getUserName()));
       map.set("total",String.valueOf(this.productService.getTotal(products)));

       HttpEntity<MultiValueMap<String,String>> request=new HttpEntity<>(map,headers);

       ResponseEntity<String> response=restTemplate.postForEntity(paymentServiceUrl+cards+payment,request,String.class);

       if(response.getStatusCode().equals(HttpStatus.OK)){
           order.setPaymentStatus("paid");
           this.saveOrder(order,products);
       }
   }
//TODO: change the inStock value before saving the order
    public void saveOrder(Order order, List<Product> products) {
        log.info("Transaction \"saveOrder\" should have started here");
        this.addressService.save(order.getAddress());
        order.getUser().getAddresses().add(order.getAddress());
        this.userService.saveUser(order.getUser());
        order.setProducts(products);
        this.orderRepository.save(order);
        log.info( "we have saved::" + order.toString());
        log.info("transaction \"saveOrder\" should have been closed here");
    }

    @Transactional
    public Set<Order> findUserOrders(String login) {

        return this.orderRepository.findAllByUser_Login(login);

    }

    public Order findOrder(Long id) {

        Optional<Order> order = this.orderRepository.findById(id);

        if (order.isPresent()) {
            return order.get();

        } else {
            log.info("There is no product with id: " + id.toString());
            throw new RuntimeException();
        }
    }
//TODO: figure out if the return value is needed
    public Order createNewOrder(Order order) {

        User user = this.userService.getUser();
        order.setUser(user);
        Address currentAddress = this.addressService.userCurrentAddress(user);
        if (currentAddress != null) {
            order.setAddress(currentAddress);
        }
        order.setOrderStatus("waiting for approval");
        order.setPaymentStatus("not paid");
        log.info("after creating a new order: " +order);
        return order;
    }

}

