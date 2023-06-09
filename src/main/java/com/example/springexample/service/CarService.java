package com.example.springexample.service;

import com.example.springexample.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> selAll();
    List<Car> selAvailable();

    void insertCar(Car car);
    void deleteCar(int id);

}
