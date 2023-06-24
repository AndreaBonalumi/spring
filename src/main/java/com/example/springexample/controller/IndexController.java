package com.example.springexample.controller;

import com.example.springexample.entity.Booking;
import com.example.springexample.entity.User;
import com.example.springexample.service.BookingService;
import com.example.springexample.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
	private static final Logger Logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	@Qualifier("persistentTokenRepository")
	private PersistentTokenRepository persistentTokenRepository;
	@Autowired
	BookingService bookingService;
	@Autowired
	UserService userService;
	@RequestMapping
	public String loginRequest(Model model) {

		if (!userService.thereIsAdmin()) {
			userService.saveNewAdmin();
		}

		return "index";
	}
	@RequestMapping("login")
	public String login() {

		if (!userService.thereIsAdmin()) {
			userService.saveNewAdmin();
		}

		return "index";
	}

	@GetMapping("home")
	public String viewHome(Model model){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String username = userDetails.getUsername();
			User userLogin = userService.getUserByUsername(username);

			model.addAttribute("userLogger", userLogin);

			if (userLogin.isAdmin()) {
				List<User> users = userService.getAllUsers();
				model.addAttribute("users", users);
			} else {
				List<Booking> bookings = bookingService.selBookingsByIdUser(userLogin.getId());
				model.addAttribute("bookings", bookings);
			}

			return "home";
		}
		return "redirect:/login?fail";
	}

	@PostMapping("home")
	public String loginResponse(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String username = userDetails.getUsername();
			User userLogin = userService.getUserByUsername(username);

			if (userLogin != null) {
				model.addAttribute("userLogger", userLogin);
				if (userLogin.isAdmin()) {
					Object filter = new Object();
					model.addAttribute("filter", filter);
					List<User> users = userService.getAllUsers();
					model.addAttribute("users", users);
				} else {
					List<Booking> bookings = bookingService.selBookingsByIdUser(userLogin.getId());
					model.addAttribute("bookings", bookings);
				}

				return "home";
			}
		}
		return "redirect:/login?fail";
	}

	@GetMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		String[] test = request.getParameterValues("logout");

		if (test != null) {
			Cookie cookieWithSlash = new Cookie("JSESSIONID", null);

			cookieWithSlash.setPath(request.getContextPath() + "/");
			cookieWithSlash.setMaxAge(0);

			Cookie cookieWithOutSlash = new Cookie("JSESSIONID", null);
			cookieWithOutSlash.setPath(request.getContextPath());
			cookieWithOutSlash.setMaxAge(0);


			response.addCookie(cookieWithSlash);
			response.addCookie(cookieWithOutSlash);

			if (test.length == 2) {
				Logger.info("utente" + test[1]);
				persistentTokenRepository.removeUserTokens(test[1]);
			}
		}
		return "redirect:/login?logout";
	}

}