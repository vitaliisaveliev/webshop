package com.epam.db.dao;

import com.epam.db.entity.Card;

public interface CardDAO {

    void addCard(Card card, int userId);
}
