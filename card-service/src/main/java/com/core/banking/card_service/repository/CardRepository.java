package com.core.banking.card_service.repository;

import com.core.banking.card_service.entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String> {

    List<Card> findByCustomerId(Long customerId);
}
