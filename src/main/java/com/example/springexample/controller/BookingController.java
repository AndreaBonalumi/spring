package com.example.springexample.controller;

import com.example.springexample.entity.Booking;
import com.example.springexample.entity.Car;
import com.example.springexample.repository.impl.CarDaoImpl;
import com.example.springexample.service.BookingService;
import com.example.springexample.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;
    @Autowired
    CarService carService;

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
            List<Car> cars = carService.getCarsByDate(booking.getDateBookingStart(), booking.getDateBookingEnd());

            model.addAttribute("cars", cars);

            return "newBooking";

        } else {
            booking.setStatus(0);
            booking.setCar(car);
            bookingService.manageBooking(booking);

            return "redirect:/springExample_war_exploded/home";
        }
    }

}
