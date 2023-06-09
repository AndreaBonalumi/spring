package com.example.springexample.repository;

import com.example.springexample.entity.Car;

import java.util.List;

public interface CarRepository {

    List<Car> selAll();
    List<Car> selAvailable();
    void insertCar(Car car);
    void deleteCar(int id);
    // void editCar(Car car);


}
