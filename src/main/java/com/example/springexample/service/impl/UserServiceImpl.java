package com.example.springexample.service.impl;

import com.example.springexample.entity.User;
import com.example.springexample.repository.UserDao;
import com.example.springexample.repository.impl.UserDaoImpl;
import com.example.springexample.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUser();
    }

    @Override
    public List<User> searchUser(String field, String searchText) {
        return userDao.searchUsers(field, searchText);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

     @Override
     public User getUserByUsername(String username) {return userDao.getUserByUsername(username); }

    @Override
    public User getUserByLogin(String username, String password) {
        return userDao.getUserByUsPw(username, password);
    }

    @Override
    public void manageUser(User user) {
        userDao.manageUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
}
