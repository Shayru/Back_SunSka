package com.akthon.SunSka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Event {

    private @Id
    @GeneratedValue Long id;

    private Date startDate;

    private Date endDate;

    private Integer year;


    public Event() {
    }

    public Event(Long id, Date startDate, Date endDate, Integer year) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.year = year;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
