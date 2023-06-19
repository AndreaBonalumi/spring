package com.example.springexample.controller;

import com.example.springexample.entity.Booking;
import com.example.springexample.entity.Car;
import com.example.springexample.entity.User;
import com.example.springexample.service.BookingService;
import com.example.springexample.service.CarService;
import com.example.springexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    @RequestMapping(value = "manage/{id}", method = RequestMethod.GET)
    public String newBooking(@PathVariable("id") int id, Model model) {
        Booking booking = null;
        if (id == -1) {
            booking = new Booking();
            model.addAttribute("bookingRequest", booking);

            return "newBooking";
        }
         booking = bookingService.selBookingById(id);
        if (booking == null) {
            booking = new Booking();
            model.addAttribute("bookingRequest", booking);

            return "newBooking";
        } else {
            model.addAttribute("bookingRequest", booking);
            return "redirect:/booking/completeBooking";
        }
    }

    @GetMapping("completeBooking")
    public String seeAvailable(@ModelAttribute("bookingRequest") Booking booking,
                               Model model) {

        List<Car> cars = bookingService.getBookingByDate(booking.getDateBookingStart(), booking.getDateBookingEnd());

        model.addAttribute("completeBooking", booking);
        model.addAttribute("cars", cars);

        return "newBooking";
    }
    @PostMapping("completeBooking")
    public String insertBooking(@ModelAttribute("completeBooking") Booking booking, HttpSession session) {

        booking.setStatus(0);

        User user = (User) session.getAttribute("userLogger");
        booking.setUser(userService.getUserById(user.getId()));

        bookingService.manageBooking(booking);

        return "redirect:/home";
    }

    @GetMapping("delete/{id}")
    public String deleteBooking(@PathVariable("id") int id) {
        Booking booking = bookingService.selBookingById(id);
        bookingService.deleteBooking(booking);

        return "redirect:/home";
    }

}
