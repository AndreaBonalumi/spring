package com.example.springexample.service;

import com.example.springexample.entity.Booking;
import com.example.springexample.entity.Car;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    List<Booking> selAllBookings();
    Booking selBookingById(int id);
    List<Booking> selBookingsByIdUser(int id);
    List<Car> getBookingByDate(LocalDate start, LocalDate end);
    void deleteBooking(Booking booking);
    void manageBooking(Booking booking);
}
