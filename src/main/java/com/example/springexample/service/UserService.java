package com.example.springexample.service;

import com.example.springexample.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(int id);
    User getByLogin(String username, String password);
    void insert(User user);
    void delete(User user);
    void edit(User user);
}
