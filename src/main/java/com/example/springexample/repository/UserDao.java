package com.example.springexample.repository;


import com.example.springexample.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();
    User getById(int id);
    User getByUsPw(String username, String password);
    List<User> searchUsers(String field, String value);
    void edit(User user);
    void delete(User user);
    void insert(User user);

}
