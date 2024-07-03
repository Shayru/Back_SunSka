package com.akthon.SunSka.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Sales {

    private @Id
    @GeneratedValue Long id;
    private Date date;

    private Integer nbSold;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;


    public Sales() {
    }

    public Sales(
            Long id,
            Date date,
            Integer nbSold,
            User user,
            Product product,
            Stock stock
    ) {
        this.id = id;
        this.date = date;
        this.nbSold = nbSold;
        this.user = user;
        this.product = product;
        this.stock = stock;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
