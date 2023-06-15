package com.example.springexample.service.impl;

import com.example.springexample.entity.Car;
import com.example.springexample.repository.CarDao;
import com.example.springexample.repository.impl.CarDaoImpl;
import com.example.springexample.service.CarService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    CarDao carDao = new CarDaoImpl();

    @Override
    public List<Car> selAllCars() {
        return carDao.getAllCar();
    }

    @Override
    public Car getCarById(int id) { return carDao.getCarById(id); }

    @Override
    public List<Car> getCarsByDate(LocalDate start, LocalDate end) { return carDao.getCarByDate(start, end); }

    @Override
    public void manageCar(Car car) {
        carDao.manageCar(car);
    }

    @Override
    public void deleteCar(int id) {
        carDao.deleteCar(id);
    }
}
