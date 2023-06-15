package com.example.springexample.service;

import com.example.springexample.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> selAllBookings();
    Booking selBookingById(int id);
    List<Booking> selBookingsByIdUser(int id);
    void deleteBooking(Booking booking);
    void manageBooking(Booking booking);
}
