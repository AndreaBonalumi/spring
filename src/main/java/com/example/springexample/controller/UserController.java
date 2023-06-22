package com.example.springexample.controller;

import com.example.springexample.entity.Booking;
import com.example.springexample.entity.User;
import com.example.springexample.service.BookingService;
import com.example.springexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String getProfileUser(HttpSession session, Model model) {

        User user = (User) session.getAttribute("userLogger");
        model.addAttribute("user", user);
        return "profileUser";
    }

    @GetMapping("detail/{id}")
    public String getBookingsByUser(@PathVariable("id") int id, Model model) {

        List<Booking> bookings = bookingService.selBookingsByIdUser(id);
        User user = userService.getUserById(id);

        model.addAttribute("bookings", bookings);
        model.addAttribute("userBooking", user);

        return "userBooking";
    }

    @GetMapping("detail/{id}/{status}/{idBooking}")
    public String changeStatusBooking(@PathVariable("id") int id,
                                      @PathVariable("status") String status,
                                      @PathVariable("idBooking") int idBooking) {

        Booking booking = bookingService.selBookingById(idBooking);
        bookingService.deleteBooking(booking);
        if (status.equals("approve")) {
            booking.setStatus(1);
        } else {
            booking.setStatus(2);
        }
        bookingService.manageBooking(booking);

        return "redirect:/user/detail/" + id;
    }

    @GetMapping("filter")
    public String FilterUsers(@RequestParam("field") String field, @RequestParam("filter") String searchText, Model model) {

        List<User> users = userService.searchUser(field, searchText);

        model.addAttribute("users", users);

        return "home";
    }

    @RequestMapping(value = "manage/{id}", method = RequestMethod.GET)
    public String manageUserRequest(@PathVariable("id") int id, Model model) {

        User user = new User();
        if(userService.getUserById(id) != null) {
            user = userService.getUserById(id);
        }

        model.addAttribute("userRequest", user);

        return "editUser";
    }

    @PostMapping("manage/{id}")
    public String manageUser(@Valid @ModelAttribute("userRequest") User userRequest, BindingResult bindingResult, HttpSession session) {

        if(bindingResult.hasErrors())
            return "editUser";
        userRequest.setAdmin(false);
        userRequest.setEmail(userRequest.getFirstName() + "." + userRequest.getLastName() + "@si2001.it");
        userRequest.setCreated(LocalDate.now());
        User userLogger = (User) session.getAttribute("userLogger");
        if (userRequest.getId() == userLogger.getId()) {
            session.removeAttribute("userLogger");
            session.setAttribute("userLogger", userRequest);
        }


        userService.manageUser(userRequest);

        return "redirect:/home";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") int id){

        User user = userService.getUserById(id);
        userService.deleteUser(user);

        return "redirect:/home";
    }
}
