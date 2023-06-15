package com.example.springexample.service.impl;

import com.example.springexample.entity.Booking;
import com.example.springexample.repository.BookingDao;
import com.example.springexample.repository.impl.BookingDaoImpl;
import com.example.springexample.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    BookingDao bookingDao = new BookingDaoImpl();
    @Override
    public List<Booking> selAllBookings() {
        return  bookingDao.getAllBooking();
    }

    @Override
    public Booking selBookingById(int id) {
        return bookingDao.getBookingById(id);
    }

    @Override
    public List<Booking> selBookingsByIdUser(int id) {
        return bookingDao.getAllBookingByUserId(id);
    }

    @Override
    public void deleteBooking(Booking booking) {
        bookingDao.deleteBooking(booking);
    }

    @Override
    public void manageBooking(Booking booking) {
        bookingDao.manageBooking(booking);
    }
}
