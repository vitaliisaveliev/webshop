package com.epam.service.impl;

import com.epam.db.dao.CardDAO;
import com.epam.db.entity.Card;
import com.epam.service.CardService;

public class CardServiceImpl implements CardService {

    private CardDAO cardDAO;

    public CardServiceImpl(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    @Override
    public void addCard(Card card, int userId) {
        cardDAO.addCard(card, userId);
    }
}
