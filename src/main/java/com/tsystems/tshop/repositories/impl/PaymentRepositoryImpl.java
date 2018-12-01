package com.tsystems.tshop.repositories.impl;

import com.tsystems.tshop.domain.CardWithdrawal;
import com.tsystems.tshop.repositories.PaymentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


//@PropertySource("classpath:/properties/application-default.properties")
public class PaymentRepositoryImpl implements PaymentRepository {

    private static final Logger LOGGER = LogManager.getLogger(PaymentRepositoryImpl.class);

    private final RestTemplate restTemplate;

    @Value("${payment.protocol}")
    private String protocol;

    @Value("${payment.host}")
    private String host;

    @Value("${payment.port}")
    private String port;

    @Value("${payment.path}")
    private String path;

    private String paymentServiceUrl;

    @Autowired
    public PaymentRepositoryImpl(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;

    }

    public ResponseEntity<String> processCard(CardWithdrawal cardData) {

        paymentServiceUrl = UriComponentsBuilder.
                newInstance()
                .scheme(protocol)
                .host(host)
                .port(port)
                .path(path)
                .build()
                .toUriString();

        LOGGER.info("paymentServiceUrl is: {}", paymentServiceUrl);

        HttpEntity<CardWithdrawal> request = new HttpEntity<>(cardData);

        return restTemplate
                .postForEntity(paymentServiceUrl, request, String.class);
    }
}
