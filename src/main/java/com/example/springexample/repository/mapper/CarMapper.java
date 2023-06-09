package com.example.springexample.repository.mapper;

import com.example.springexample.entity.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CarMapper implements RowMapper<Car> {

    public Car mapRow(ResultSet row, int rowNum) throws SQLException {
        Car car = new Car();

        try {
            car.setId(row.getInt("id"));
            car.setBrand(row.getString("brand").trim());
            car.setModel(row.getString("model").trim());
            car.setColor(row.getString("color").trim());
            car.setCreated((LocalDate) row.getObject("created"));
            car.setLink(row.getString("link").trim());
            car.setYear((LocalDate) row.getObject("year"));
        } catch (Exception e) {
            System.out.println(e);
        }

        return car;
    }
}
