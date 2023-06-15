package com.example.springexample.controller;

import com.example.springexample.entity.Booking;
import com.example.springexample.entity.Car;
import com.example.springexample.repository.impl.BookingDaoImpl;
import com.example.springexample.repository.impl.CarDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    BookingDaoImpl bookingDao;
    CarDaoImpl carDao;

    @RequestMapping(value = "manage/{id}", method = RequestMethod.GET)
    public String newBooking(Model model){

        Booking booking = new Booking();
        model.addAttribute("booking", booking);

        return "newBooking";
    }

    @PostMapping("manage/{id}")
    public String insertBooking(@ModelAttribute("booking") Booking booking, @ModelAttribute("car") Car car, Model model) {

        if (car == null) {
            booking.setDateBookingStart((LocalDate) booking.getDateBookingStart());
            booking.setDateBookingEnd((LocalDate) booking.getDateBookingEnd());
            List<Car> cars = carDao.getCarByDate(booking.getDateBookingStart(), booking.getDateBookingEnd());

            model.addAttribute("cars", cars);

            return "newBooking";

        } else {
            booking.setStatus(0);
            booking.setCar(car);
            bookingDao.manageBooking(booking);

            return "home";
        }
    }

}
