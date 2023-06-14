package com.example.springexample.service;

import com.example.springexample.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> selAll();
    Booking selById(int id);
    List<Booking> selByIdUser(int id);
    void delete(Booking booking);
    void insert(Booking booking);
    void edit(Booking booking);
}
