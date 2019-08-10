package com.epam.db.dao;

import com.epam.db.entity.User;

public interface UserDAO {

    void addUser(User user);

    User getUser(String email, String password);

}
