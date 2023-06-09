package com.example.springexample.service.impl;

import com.example.springexample.entity.Car;
import com.example.springexample.repository.CarRepository;
import com.example.springexample.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<Car> selAll() {
        return carRepository.selAll();
    }

    @Override
    public List<Car> selAvailable() {
        return carRepository.selAvailable();
    }

    @Override
    public void insertCar(Car car) {
        carRepository.insertCar(car);
    }

    @Override
    public void deleteCar(int id) {
        carRepository.deleteCar(id);
    }
}
