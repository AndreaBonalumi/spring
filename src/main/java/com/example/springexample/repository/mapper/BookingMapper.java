package com.example.springexample.repository.mapper;

import com.example.springexample.entity.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookingMapper {
    public Booking mapRow(ResultSet row, int rowNum) throws SQLException {
        Booking booking = new Booking();

        try {
            booking.setId(row.getInt("id"));

            booking.setDateBookingStart((LocalDate) row.getObject("dateBookingStart"));
            booking.setDateBookingEnd((LocalDate) row.getObject("dateBookingEnd"));
        } catch (Exception e) {

            System.out.println(e);

        }

        return booking;
    }
}
