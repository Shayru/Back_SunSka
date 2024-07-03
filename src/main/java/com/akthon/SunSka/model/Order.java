package com.akthon.SunSka.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "\"order\"")
public class Order {

    private @Id
    @GeneratedValue Long id;

    @Column(name = "\"date\"")
    private Date date;

    private String status;

    private Date dateRestock;

    @OneToMany(mappedBy = "order")
    private Set<StockOrder> stockOrders;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {
    }

    public Order(
            Long id,
            Date date,
            String status,
            Date dateRestock,
            Set<StockOrder> stockOrders,
            User user
    ) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.dateRestock = dateRestock;
        this.stockOrders = stockOrders;
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

    public Date getDateRestock() {
        return dateRestock;
    }

    public void setDateRestock(Date dateRestock) {
        this.dateRestock = dateRestock;
    }

    public Set<StockOrder> getStockOrders() {
        return stockOrders;
    }

    public void setStockOrders(Set<StockOrder> stockOrders) {
        this.stockOrders = stockOrders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
