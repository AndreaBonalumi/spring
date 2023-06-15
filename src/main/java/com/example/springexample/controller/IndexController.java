package com.example.springexample.controller;

import com.example.springexample.entity.Booking;
import com.example.springexample.entity.User;
import com.example.springexample.service.BookingService;
import com.example.springexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController 
{
	@Autowired
	BookingService bookingService;
	@Autowired
	UserService userService;
	@RequestMapping
	public String loginRequest(Model model) {

		User user = new User();
		model.addAttribute("loginRequest", user);
		
		return "index";
	}

	@PostMapping
	public String loginResponse(@ModelAttribute("loginRequest") User user, Model model) {
		User userLogin = userService.getUserByLogin(user.getUsername(), user.getPassword());

		if (userLogin != null) {

			model.addAttribute("userLogger", userLogin);
			if (userLogin.isAdmin()) {
				List<User> users = userService.getAllUsers();
				model.addAttribute("users", users);
			} else {
				List<Booking> bookings = bookingService.selBookingsByIdUser(userLogin.getId());
				model.addAttribute("bookings", bookings);
			}

			return "home";

		} else {
			model.addAttribute("error", "Username o password non esistenti");
			return "index";
		}
	}
}
