package com.akthon.SunSka.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Event {

    private @Id
    @GeneratedValue Long id;

    private Date startDate;

    private Date endDate;

    @Column(name = "\"year\"")
    private Integer year;

    @OneToMany(mappedBy = "event")
    private Set<Stock> stocks;


    public Event() {
    }

    public Event(
            Long id,
            Date startDate,
            Date endDate,
            Integer year,
            Set<Stock> stocks
    ) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.year = year;
        this.stocks = stocks;
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

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }
}
