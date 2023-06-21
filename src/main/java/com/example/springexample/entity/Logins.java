package com.example.springexample.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Logins implements Serializable {

    @Id
    @Basic(optional = false)
    private String serie;

    @Basic(optional = false)
    private String username;

    @Basic(optional = false)
    private String token;

    @Temporal(TemporalType.TIME)
    @Basic(optional = false)
    private Date used;

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getUsed() {
        return used;
    }

    public void setUsed(Date used) {
        this.used = used;
    }
}
