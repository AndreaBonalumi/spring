package com.example.springexample.repository.mapper;

import com.example.springexample.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapper {
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        User user = new User();

        try {
            user.setId(row.getInt("id"));

            user.setUsername(row.getString("username").trim());
            user.setPassword(row.getString("password").trim());
            user.setFirstName(row.getString("firstName").trim());
            user.setLastName(row.getString("lastName").trim());
            user.setEmail(row.getString("email").trim());
            user.setAdmin(row.getBoolean("admin"));
            user.setCreated((LocalDate) row.getObject("created"));
            user.setBirthday((LocalDate) row.getObject("birthday"));
        } catch (Exception e) {

            System.out.println(e);

        }

        return user;
    }
}
