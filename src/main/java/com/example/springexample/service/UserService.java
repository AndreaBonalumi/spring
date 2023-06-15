package com.example.springexample.service;

import com.example.springexample.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByLogin(String username, String password);
    void manageUser(User user);
    void deleteUser(User user);
}
