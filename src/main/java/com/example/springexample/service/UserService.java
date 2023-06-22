package com.example.springexample.service;

import com.example.springexample.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    List<User> searchUser(String field, String searchText);
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByLogin(String username, String password);
    void manageUser(User user);
    void deleteUser(User user);
}
