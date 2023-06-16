package com.example.springexample.service;

import com.example.springexample.entity.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarService {
    List<Car> selAllCars();
    Car getCarById(int id);
    List<Car> getCarsByDate(LocalDate start, LocalDate end);
    void manageCar(Car car);
    void deleteCar(Car car);
    // void editCar(Car car);

}
