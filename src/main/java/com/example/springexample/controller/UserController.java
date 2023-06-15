package com.example.springexample.controller;

import com.example.springexample.entity.User;
import com.example.springexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public String getProfileUser(@PathVariable("id") int id, Model model) {

        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return null;
    }

    @RequestMapping(value = "manage/{id}", method = RequestMethod.GET)
    public String manageUserRequest(@PathVariable("id") int id, Model model) {

        User user = new User();
        if(userService.getUserById(id) != null) {
            user = userService.getUserById(id);
        }

        model.addAttribute("user", user);

        return "editUser";
    }

    @PostMapping("manage/{id}")
    public String manageUser(@ModelAttribute("user") User user) {

        userService.manageUser(user);

        return "home";
    }

    @PostMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") int id){

        User user = userService.getUserById(id);
        userService.deleteUser(user);

        return "home";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        userService.deleteUser(user);

        return "home";
    }
}
