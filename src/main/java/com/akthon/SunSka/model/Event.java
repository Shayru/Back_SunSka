package com.akthon.SunSka.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Event {

    private @Id
    @GeneratedValue Long id;

    private Date startDate;

    private Date endDate;

    @Column(name = "\"year\"")
    private Integer year;

    @OneToMany(mappedBy = "event")
    private Set<Sales> sales;

    @OneToMany(mappedBy = "event")
    private Set<Stock> stocks;


    public Event() {
    }

    public Event(
            Long id,
            Date startDate,
            Date endDate,
            Integer year,
            Set<Sales> sales,
            Set<Stock> stocks
    ) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.year = year;
        this.sales = sales;
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

    public Set<Sales> getSales() {
        return sales;
    }

    public void setSales(Set<Sales> sales) {
        this.sales = sales;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }
}
