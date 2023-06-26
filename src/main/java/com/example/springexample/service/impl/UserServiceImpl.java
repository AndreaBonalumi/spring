package com.example.springexample.service.impl;

import com.example.springexample.entity.Booking;
import com.example.springexample.entity.User;
import com.example.springexample.repository.UserDao;
import com.example.springexample.repository.impl.UserDaoImpl;
import com.example.springexample.service.BookingService;
import com.example.springexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;
    @Autowired
    BookingService bookingService;

    @Autowired
    private BCryptPasswordEncoder encoder;

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

    @Override
    public boolean thereIsAdmin() {
        return userDao.thereIsAdmin();
    }

    @Override
    public void saveNewAdmin() {
        userDao.saveNewAdmin(encoder.encode("admin1"));
    }
    public boolean checkUserId(Authentication authentication, int id) {

        if (id == -1)
            return false;

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;

            User user = getUserByUsername(userDetails.getUsername());
            return user.getId() == getUserById(id).getId();
        } else
            return false;
    }

    public boolean checkBookingId(Authentication authentication, int id) {

        if (id == -1)
            return false;

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;

            User user = getUserByUsername(userDetails.getUsername());

            List<Booking> bookings = bookingService.selBookingsByIdUser(user.getId());
            if (bookings == null || bookings.size() == 0)
                return false;
            for (Booking booking : bookings) {
                if (booking.getId() == id && booking.getUser().getId() == user.getId())
                    return true;
            }
        }
        return false;

    }

}
