package com.epam.service;

import com.epam.db.entity.Card;

public interface CardService {

    void addCard(Card card, int userId);
}
