package com.example.springexample.controller;

import com.example.springexample.entity.User;
import com.example.springexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {

        User user = userService.getById(id);
        model.addAttribute("user", user);
        return null;
    }

    @RequestMapping("/new")
    public String newUser(Model model){
        User user = new User();
        model.addAttribute("newUserRequest", user);

        return "newUser.jsp";
    }

    @PostMapping("/new")
    public String insertUser(@ModelAttribute("newUserRequest") User user) {
        userService.insert(user);

        return "home.jsp";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        User user = userService.getById(id);
        userService.delete(user);

        return "home.jsp";
    }
}
