package com.example.springexample.repository;


import com.example.springexample.entity.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarDao {

    List<Car> getAllCar();
    List<Car> getCarByDate(LocalDate start, LocalDate end);
    Car getCarById(int id);
    void manageCar(Car car);
    void deleteCar(int car);
}
