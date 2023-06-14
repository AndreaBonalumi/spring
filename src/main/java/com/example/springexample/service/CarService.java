package com.example.springexample.service;

import com.example.springexample.entity.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarService {
    List<Car> selAll();
    Car getById(int id);
    List<Car> getByDate(LocalDate start, LocalDate end);
    void insertCar(Car car);
    void deleteCar(int id);
    // void editCar(Car car);

}
