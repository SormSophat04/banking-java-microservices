package com.core.banking.card_service.mapper;

import com.core.banking.card_service.dto.CardDTO;
import com.core.banking.card_service.entity.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {
    Card toCard(CardDTO dto);
    CardDTO toCardDTO(Card entity);
}
