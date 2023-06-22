package com.example.springexample.repository;


import com.example.springexample.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUser();
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByUsPw(String username, String password);
    List<User> searchUsers(String field, String value);
    void deleteUser(User user);
    void manageUser(User user);

}
