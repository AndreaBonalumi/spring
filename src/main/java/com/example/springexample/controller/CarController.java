package com.example.springexample.controller;

import com.example.springexample.entity.Car;
import com.example.springexample.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    private List<Car> recordset;
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllCar(Model model) {
        recordset = carService.selAll();

        model.addAttribute("Cars", recordset);

        return "cars";
    }


}
