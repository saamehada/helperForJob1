package com.example.interviewBot.service;

import com.example.interviewBot.model.User;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> userStorage = new HashMap<>();

    public boolean register(String username, String password) {
        if (userStorage.containsKey(username)) {
            return false;
        }
        userStorage.put(username, new User(username, password));
        return true;
    }

    public boolean login(String username, String password) {
        User user = userStorage.get(username);
        return user != null && user.getPassword().equals(password);
    }

    public User getUser(String username) {
        return userStorage.get(username);
    }

    public boolean userExists(String username) {
        return userStorage.containsKey(username);
    }

    public void updateUser(User user) {
        if (user != null && user.getUsername() != null) {
            userStorage.put(user.getUsername(), user);
        }
    }
}