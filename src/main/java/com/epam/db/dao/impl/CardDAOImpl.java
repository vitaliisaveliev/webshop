package com.epam.db.dao.impl;

import com.epam.db.DBManager;
import com.epam.db.dao.CardDAO;
import com.epam.db.entity.Card;
import com.epam.exception.DBException;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.epam.constant.SQLConstants.SQL_INSERT_CARD;
import static com.epam.constant.SQLConstants.SQL_INSERT_CARDS_ON_USER;

public class CardDAOImpl implements CardDAO {

    @Override
    public void addCard(Card card, int userId) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            insertCard(con, card);
            insertCardOnUser(con, card, userId);
            con.commit();
        } catch (SQLException e) {
            DbUtils.rollbackAndCloseQuietly(con);
            throw new DBException(e.getMessage());
        }
    }

    private void insertCard(Connection con, Card card) {
        try (PreparedStatement ps = con.prepareStatement(SQL_INSERT_CARD)) {
            ps.setInt(1, card.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    private void insertCardOnUser(Connection con, Card card, int userId) {
        try (PreparedStatement ps = con.prepareStatement(SQL_INSERT_CARDS_ON_USER)) {
            ps.setInt(1, userId);
            ps.setInt(2, card.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}
