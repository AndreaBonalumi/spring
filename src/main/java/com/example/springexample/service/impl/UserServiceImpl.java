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
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public User getByLogin(String username, String password) {
        return userDao.getByUsPw(username, password);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void edit(User user) {
        userDao.edit(user);
    }
}
