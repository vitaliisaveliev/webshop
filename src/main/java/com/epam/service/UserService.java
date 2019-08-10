package com.epam.service;


import com.epam.db.entity.User;
import com.epam.exception.DuplicateUserException;

public interface UserService {

    void addUser(User user) throws DuplicateUserException;

    User getUser(String email, String password);
}
