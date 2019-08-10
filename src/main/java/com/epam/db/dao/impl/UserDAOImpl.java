package com.epam.db.dao.impl;

import com.epam.db.DBManager;
import com.epam.db.dao.UserDAO;
import com.epam.db.entity.User;
import com.epam.exception.DBException;
import org.apache.commons.dbutils.DbUtils;

import java.sql.*;

import static com.epam.constant.SQLConstants.*;

public class UserDAOImpl implements UserDAO {

    @Override
    public void addUser(User user) {
        try (Connection con = DBManager.getInstance().getConnection()) {
            insertUser(con, user);
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
    }

    @Override
    public User getUser(String email, String password) {
        User user = null;
        ResultSet rs = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQL_GET_USER_BY_EMAIL)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
        }
        return user;
    }

    private void insertUser(Connection con, User user) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            int k = 1;
            ps = con.prepareStatement(SQL_ADD_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(k++, user.getName());
            ps.setString(k++, user.getSurname());
            ps.setString(k++, user.getEmail());
            ps.setString(k++, user.getPassword());
            ps.setString(k, user.getCountry());
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int userId = rs.getInt(1);
                    user.setId(userId);
                }
            }
            con.commit();
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(ID));
        user.setName(rs.getString(NAME));
        user.setSurname(rs.getString(SURNAME));
        user.setEmail(rs.getString(EMAIL));
        user.setPassword(rs.getString(PASSWORD));
        user.setCountry(rs.getString(COUNTRY));
        return user;
    }
}
