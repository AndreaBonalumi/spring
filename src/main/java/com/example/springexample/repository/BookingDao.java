package com.example.springexample.repository;

import com.example.springexample.entity.Booking;
import com.example.springexample.entity.Car;

import java.time.LocalDate;
import java.util.List;

public interface BookingDao {
    List<Booking> getAllBooking();
    List<Booking> getAllBookingByUserId(int id);
    Booking getBookingById(int id);
    List<Car> getBookingByDate(LocalDate start, LocalDate end);
    void manageBooking(Booking booking);
    void deleteBooking(Booking booking);
}
