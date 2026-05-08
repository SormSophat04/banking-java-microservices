package com.core.banking.card_service.service;

import com.core.banking.card_service.entity.Card;

import java.util.List;

public interface CardService {

    Card create(Card card);
    List<Card> getAllCard();

    List<Card> getByCustomerId(Long customerId);

}
