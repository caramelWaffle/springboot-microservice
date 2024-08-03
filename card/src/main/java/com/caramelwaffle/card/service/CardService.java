package com.caramelwaffle.card.service;

import com.caramelwaffle.card.model.CardData;

public interface CardService {

    void createCard(CardData cardData);

    CardData findCard(Long cardId);

    CardData findCardByMobileNumber(String mobileNumber);

    boolean updateCard(CardData cardData);

    boolean deleteCard(Long cardId);

}
