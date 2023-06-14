package com.example.springexample.service.impl;

import com.example.springexample.entity.Booking;
import com.example.springexample.repository.BookingDao;
import com.example.springexample.repository.impl.BookingDaoImpl;
import com.example.springexample.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    BookingDao bookingDao = new BookingDaoImpl();
    @Override
    public List<Booking> selAll() {
        return  bookingDao.getAll();
    }

    @Override
    public Booking selById(int id) {
        return bookingDao.getById(id);
    }

    @Override
    public List<Booking> selByIdUser(int id) {
        return bookingDao.getAllByUserId(id);
    }

    @Override
    public void delete(Booking booking) {
        bookingDao.delete(booking);
    }

    @Override
    public void insert(Booking booking) {
        bookingDao.insert(booking);
    }

    @Override
    public void edit(Booking booking) {
        bookingDao.edit(booking);
    }
}
