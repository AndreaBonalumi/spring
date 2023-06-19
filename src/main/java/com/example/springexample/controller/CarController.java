package com.example.springexample.controller;

import com.example.springexample.entity.Car;
import com.example.springexample.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

        int id = -1;
        model.addAttribute("cars", recordset);
        model.addAttribute("id", id);

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

    @PostMapping("manage/{id}")
    public String manageCar(@ModelAttribute("carRequest") Car car) {

        car.setCreated(LocalDate.now());
        carService.manageCar(car);

        return "redirect:/car/all";
    }

    @PostMapping("delete")
    public String deleteCar(@ModelAttribute("id") int id) {

        Car car = carService.getCarById(id);
        carService.deleteCar(car);

        return "redirect:/car/all";
    }
}
