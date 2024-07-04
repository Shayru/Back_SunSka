package com.akthon.SunSka.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "\"order\"")
public class Order {

    public enum OrderType {
        ORDER_TYPE,
        SALE_TYPE,
        RESTOCK_TYPE
    }

    public enum OrderStatus {
        PENDING,
        DELIVERED,
        CANCELLED,
        CREATED
    }

    private @Id
    @GeneratedValue Long id;

    @Column(name = "\"date\"")
    private Date date;

    private OrderStatus status;

    private Date dateRestock;

    @OneToMany(mappedBy = "order")
    private Set<StockOrder> stockOrders;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    private OrderType type;

    public Order() {
    }

    public Order(
            Date date,
            OrderStatus status,
            Date dateRestock,
            Set<StockOrder> stockOrders,
            Building building,
            OrderType type
    ) {
        this.date = date;
        this.status = status;
        this.dateRestock = dateRestock;
        this.stockOrders = stockOrders;
        this.building = building;
        this.type = type;
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

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }
}
