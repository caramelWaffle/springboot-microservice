package com.caramelwaffle.card.service.impl;

import com.caramelwaffle.card.entity.CardEntity;
import com.caramelwaffle.card.exception.CardMobileNumberAlreadyExistException;
import com.caramelwaffle.card.exception.ResourceNotFoundException;
import com.caramelwaffle.card.model.CardData;
import com.caramelwaffle.card.repository.CardRepository;
import com.caramelwaffle.card.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    @Override
    public void createCard(CardData cardData) {
        Optional<CardEntity> existedMobileNumberCard = cardRepository.findByMobileNumber(cardData.getMobileNumber());
        if (existedMobileNumberCard.isPresent()) {
            throw new CardMobileNumberAlreadyExistException("Card already registered with given mobileNumber " +
                    existedMobileNumberCard.get().getMobileNumber());
        }
        cardRepository.save(cardData.toEntity(new CardEntity()));
    }

    @Override
    public CardData findCard(Long cardId) {
        CardEntity entity = cardRepository.findById(cardId).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardId", cardId.toString())
        );
        return entity.toData(new CardData());
    }

    @Override
    public CardData findCardByMobileNumber(String mobileNumber) {
        CardEntity entity = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Mobile number", mobileNumber)
        );
        return entity.toData(new CardData());
    }

    @Override
    public boolean updateCard(CardData cardData) {
        CardEntity entity = cardRepository.findById(cardData.getCardId()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardId", cardData.getCardId().toString())
        );
        CardEntity updatedEntity = cardData.toEntity(entity);
        cardRepository.save(updatedEntity);
        return true;
    }

    @Override
    public boolean deleteCard(Long cardId) {
        CardEntity entity = cardRepository.findById(cardId).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardId", cardId.toString())
        );
        cardRepository.delete(entity);
        return true;
    }
}
