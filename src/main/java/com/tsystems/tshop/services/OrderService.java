package com.tsystems.tshop.services;

import com.tsystems.tshop.controllers.MainController;
import com.tsystems.tshop.domain.*;
import com.tsystems.tshop.enums.OrderStatus;
import com.tsystems.tshop.enums.PaymentStatus;
import com.tsystems.tshop.repositories.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 */
@Service
public class OrderService {

    private static final java.util.logging.Logger log = Logger.getLogger(OrderService.class.toString());
    org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MainController.class);

    OrderRepository orderRepository;

    UserService userService;

    AddressService addressService;

    ProductService productService;

    RestTemplate restTemplate;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        UserService userService,
                        AddressService addressService,
                        ProductService productService,
                        RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.addressService = addressService;
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

//    private final RestTemplate restTemplate=new RestTemplate();
//TODO: find out how to get this uri from the properties file .
    @Value("http://localhost:8001/cards/payment")
    private String paymentServiceUrl;

//        @Value("${payment.uri}")
//    private String paymentServiceUrl;
//    @Value("${payment.protocol}")
//    private String protocol;
//
//    @Value("${payment.host}")
//    private String host;
//
//    @Value("${payment.port}")
//    private String port;
//
//    @Value("${payment.path}")
//    private String path;






//    URIBuilder builder = new URIBuilder()
//builder.setScheme("http")
//        builder.setHost("IP")
//        builder.setPath("/foldername/1234")
//        builder.addParameter("abc", "xyz")
//    URL url = builder.build().toURL()

//    private static final String cards = "/cards";
//    private static final String payment = "/payment";

    public void saveOrder(Order order){
        this.orderRepository.save(order);
        log.info( "we have saved::" + order.toString());
    }


    /**
     *
     * @param card
     * @param order
     * @param products
     */
   public void payWithCard(Card card,Order order,List<Product>products) throws RuntimeException{


//       HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
//       List<HttpMessageConverter<?>> converters=new ArrayList<>();
//       converters.add(messageConverter);
//       restTemplate.setMessageConverters((converters));

//       MultiValueMap<String,Object> map=new LinkedMultiValueMap<>();
//
//       map.add("card", card);
//       map.add("total",this.productService.getTotal(products));
//
//       HttpEntity<MultiValueMap<String,Object>> request=new HttpEntity<>(map,headers);

//       HttpHeaders headers=new HttpHeaders();
//       headers.setContentType(MediaType.APPLICATION_JSON);

//       HttpEntity<CardWithdrawal> request=new HttpEntity<>(data,headers);
      // map.set("number", String.valueOf(card.getCardNumber()));
//       map.set("cvv", String.valueOf(card.getCvv()));
//       map.set("userName",String.valueOf(card.getUserName()));
//       map.set("total",String.valueOf(this.productService.getTotal(products)));
       //CardData cd = ...;
       // use multipartBody with RestTemplate or WebClient
//       HttpEntity<MultiValueMap<String, Object>> request
//               = new HttpEntity<>(map, headers);


//       String paymentServiceUrl=UriComponentsBuilder
//               .newInstance()
//               .scheme(protocol)
//               .host(host)
//               .port(port)
//               .path(path)
//               .build()
//               .toUriString();

       LOGGER.info("URI for PaymentService: "+paymentServiceUrl);

       CardWithdrawal data=new CardWithdrawal(card,productService.getTotal(products));
       HttpEntity<CardWithdrawal> request=new HttpEntity<>(data);
       ResponseEntity<String> response=restTemplate
               .postForEntity(paymentServiceUrl,request,String.class);


       if(response.getStatusCode().equals(HttpStatus.OK)){
           order.setPaymentStatus(PaymentStatus.PAID);
           this.saveOrder(order,products);
       }else throw new RuntimeException();
   }
//TODO: change the inStock value before saving the order
    public void saveOrder(Order order, List<Product> products) {
        log.info("Transaction \"saveOrder\" should have started here");
        this.addressService.save(order.getAddress());
        order.getUser().setAddress(order.getAddress());
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

       if(order.getOrderId()!=null){
           order.setOrderId(null);
       }else {
           User user = this.userService.getUser();
           order.setUser(user);
           Address currentAddress = user.getAddress();
           if (currentAddress != null) {
               order.setAddress(currentAddress);

           }
       }
        order.setOrderStatus(OrderStatus.PENDING_APPROVAL);
        order.setPaymentStatus(PaymentStatus.NOT_PAID);
        log.info("after creating a new order: " +order);
        return order;
    }

    public List<Product> getProductsByOrderId(Long orderId) {
       List <Product> products=new ArrayList<>();
       Optional<Order> order=this.orderRepository.findById(orderId);
       if (order.isPresent()){
           products=order.get().getProducts();
       }
       return products;
    }
}

