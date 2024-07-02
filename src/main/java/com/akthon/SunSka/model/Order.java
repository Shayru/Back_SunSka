package com.akthon.SunSka.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Order {

    private @Id
    @GeneratedValue Long id;

    private Date date;

    private String status;

    private Integer nbRestock;

    @ManyToMany(mappedBy = "orders")
    private Set<Stock> stocks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {
    }

    public Order(
            Long id,
            Date date,
            String status,
            Integer nbRestock,
            Set<Stock> stocks,
            User user
    ) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.nbRestock = nbRestock;
        this.stocks = stocks;
        this.user = user;
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

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
