package com.core.banking.card_service.controller;

import com.core.banking.card_service.dto.CardDTO;
import com.core.banking.card_service.entity.Card;
import com.core.banking.card_service.mapper.CardMapper;
import com.core.banking.card_service.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/cards")
public class CardController {
    private final CardService cardService;
    private final CardMapper cardMapper;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(cardService.getAllCard());
    }

    @PostMapping
    public ResponseEntity<?> addCard(@RequestBody CardDTO dto){
        Card card = cardMapper.toCard(dto);
        card = cardService.create(card);
        return ResponseEntity.ok(card);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<List<Card>> getCustomer(
            @RequestHeader("banking-ucl-id")  String correlationId,
            @PathVariable Long customerId
    ){
        log.debug("CorrelationId {}", correlationId);
        return  ResponseEntity.ok(cardService.getByCustomerId(customerId));
    }
}
