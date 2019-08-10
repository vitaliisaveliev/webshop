package com.epam.db.dao.impl;

import com.epam.db.DBManager;
import com.epam.db.bean.OrderBean;
import com.epam.db.dao.OrderDAO;
import com.epam.db.entity.Order;
import com.epam.exception.DBException;
import org.apache.commons.dbutils.DbUtils;

import java.sql.*;

import static com.epam.constant.SQLConstants.SQL_INSERT_ORDER;
import static com.epam.constant.SQLConstants.SQL_INSERT_ORDER_PRODUCT;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public Order createOrder(Order order) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            insertOrder(con, order);
            insertOrderedProducts(con, order);
            con.commit();
        } catch (SQLException e) {
            DbUtils.rollbackAndCloseQuietly(con);
            throw new DBException(e.getMessage());
        }
        return order;
    }

    private void insertOrder(Connection con, Order order) throws SQLException {
        int count = 1;
        try (PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(count++, order.getStatus());
            pstmt.setString(count++, order.getStatementDescription());
            pstmt.setTimestamp(count++, order.getDate());
            pstmt.setInt(count++, order.getUserID());
            pstmt.setString(count++, order.getPaymentType());
            pstmt.setString(count, order.getDeliveryType());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                order.setId(rs.getInt(1));
            }
            DbUtils.closeQuietly(rs);
        }
    }

    private void insertOrderedProducts(Connection con, Order order) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(SQL_INSERT_ORDER_PRODUCT)) {
            for (OrderBean orderedProduct : order.getOrderedProducts()) {
                int count = 1;
                preparedStatement.setInt(count++, order.getId());
                preparedStatement.setInt(count++, orderedProduct.getProductId());
                preparedStatement.setInt(count, orderedProduct.getCount());
                preparedStatement.executeUpdate();
            }
        }
    }
}
