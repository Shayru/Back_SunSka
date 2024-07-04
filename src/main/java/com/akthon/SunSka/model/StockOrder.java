package com.akthon.SunSka.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_order")
public class StockOrder {

    @Id
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer quantity;

    public StockOrder() {}

    public StockOrder(Stock stock, Order order, Integer quantity) {
        this.stock = stock;
        this.order = order;
        this.quantity = quantity;
    }


    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

