package com.example.springexample.controller;

import com.example.springexample.entity.Car;
import com.example.springexample.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    private List<Car> recordset;
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllCar(Model model) {
        recordset = carService.selAllCars();

        model.addAttribute("cars", recordset);

        return "viewCars";
    }

    @RequestMapping(value = "manage/{id}", method = RequestMethod.GET)
    public String manageCarRequest(@PathVariable("id") int id, Model model) {

        Car car = new Car();

        if(carService.getCarById(id) != null)
        {
            car = carService.getCarById(id);
        }

        model.addAttribute("carRequest", car);
        return "editCar";
    }

    @PostMapping("mange/{id}")
    public String manageCar(@ModelAttribute("carRequest") Car car) {

        carService.manageCar(car);

        return "home";
    }

    @PostMapping("delete/{id}")
    public String deleteCar(@PathVariable("id") int id) {

        carService.deleteCar(id);

        return "home";
    }
}
