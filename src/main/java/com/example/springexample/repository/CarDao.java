package com.example.springexample.repository;


import com.example.springexample.entity.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarDao {

    List<Car> getAll();
    List<Car> getByDate(LocalDate start, LocalDate end);
    Car getById(int id);
    void insert(Car car);
    void delete(int car);
    void edit(Car car);
}
