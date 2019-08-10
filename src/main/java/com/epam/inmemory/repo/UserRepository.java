package com.epam.inmemory.repo;

import com.epam.db.dao.UserDAO;
import com.epam.db.entity.User;
import com.epam.exception.DuplicateUserException;

import java.util.HashMap;
import java.util.Map;

public class UserRepository implements UserDAO {

    private static final String USER_1 = "Carl@gmail.com";
    private static final String USER_2 = "Vincent@gmail.com";
    private static final String USER_3 = "Elgardo@gmail.com";

    private Map<String, User> users;

    public UserRepository() {
        users = new HashMap<>();
        User user1 = new User();
        user1.setEmail(USER_1);
        user1.setPassword("Asd12345");
        User user2 = new User();
        user2.setEmail(USER_2);
        user2.setPassword("Asd12345");
        User user3 = new User();
        user3.setEmail(USER_3);
        user3.setPassword("Asd12345");
        users.put(USER_1, user1);
        users.put(USER_2, user2);
        users.put(USER_3, user3);
    }

    public void addUser(User user) {
        if (!users.containsKey(user.getEmail())) {
            users.put(user.getEmail(), user);
            return;
        }
        throw new DuplicateUserException("User with this email is already exist");
    }

    public User getUser(String email, String password) {
        if (users.get(email) != null && users.get(email).getPassword().equals(password)) {
            return users.get(email);
        }
        return null;
    }
}
