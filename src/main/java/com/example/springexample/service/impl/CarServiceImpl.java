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
    public List<Car> selAll() {
        return carDao.getAll();
    }

    @Override
    public Car getById(int id) { return carDao.getById(id); }

    @Override
    public List<Car> getByDate(LocalDate start, LocalDate end) { return carDao.getByDate(start, end); }

    @Override
    public void insertCar(Car car) {
        carDao.insert(car);
    }

    @Override
    public void deleteCar(int id) {
        carDao.delete(id);
    }
}
