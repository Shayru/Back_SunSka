package com.akthon.SunSka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Order {

    private @Id
    @GeneratedValue Long id;

    private Date date;

    private String status;

    private Integer nbRestock;


    public Order() {
    }

    public Order(Long id, Date date, String status, Integer nbRestock) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.nbRestock = nbRestock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNbRestock() {
        return nbRestock;
    }

    public void setNbRestock(Integer nbRestock) {
        this.nbRestock = nbRestock;
    }
}
