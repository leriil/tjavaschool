package com.tsystems.tshop.repositories;

import com.tsystems.tshop.domain.CardWithdrawal;
import org.springframework.http.ResponseEntity;


public interface PaymentRepository {

    ResponseEntity<String> processCard(CardWithdrawal cardData);
}
