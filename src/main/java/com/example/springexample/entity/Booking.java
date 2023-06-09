package com.example.springexample.entity;

import java.time.LocalDate;

public class Booking {

    private int id;
    private LocalDate dateBookingStart;
    private LocalDate dateBookingEng;

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

    public LocalDate getDateBookingEng() {
        return dateBookingEng;
    }

    public void setDateBookingEng(LocalDate dateBookingEng) {
        this.dateBookingEng = dateBookingEng;
    }

}
