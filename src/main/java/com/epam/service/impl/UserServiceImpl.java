package com.epam.service.impl;

import com.epam.db.dao.UserDAO;
import com.epam.db.entity.User;
import com.epam.exception.DuplicateUserException;
import com.epam.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDao;

    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) throws DuplicateUserException {
        userDao.addUser(user);
    }

    @Override
    public User getUser(String email, String password) {
        return userDao.getUser(email, password);
    }
}
