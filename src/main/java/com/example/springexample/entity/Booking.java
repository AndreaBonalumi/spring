package com.example.springexample.entity;


import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateBookingStart;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateBookingEnd;
    private int status;
    @ManyToOne
    private User user;
    @ManyToOne
    private Car car;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateBookingStart() {
        return dateBookingStart;
    }

    public void setDateBookingStart(LocalDate dateBookingStart) {
        this.dateBookingStart = dateBookingStart;
    }

    public LocalDate getDateBookingEnd() {
        return dateBookingEnd;
    }

    public void setDateBookingEnd(LocalDate dateBookingEnd) {
        this.dateBookingEnd = dateBookingEnd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


}
