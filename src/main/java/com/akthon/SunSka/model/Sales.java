package com.akthon.SunSka.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Sales {

    private @Id
    @GeneratedValue Long id;
    private Date createdAt;

    private Integer nbSold;

    // false = vente
    // true = restock
    private Boolean positive;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    public Sales() {
    }

    public Sales(
            Long id,
            Date createdAt,
            Integer nbSold,
            User user,
            Stock stock,
            Boolean positive
    ) {
        this.id = id;
        this.createdAt = null;
        this.nbSold = nbSold;
        this.user = user;
        this.stock = stock;
        this.positive = positive;
    }


    public Integer getNbSold() {
        return nbSold;
    }

    public void setNbSold(Integer nbSold) {
        this.nbSold = nbSold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getPositive() {
        return positive;
    }

    public void setPositive(Boolean positive) {
        this.positive = positive;
    }
}
