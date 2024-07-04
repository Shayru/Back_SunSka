package com.akthon.SunSka.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"order\"")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Order {

    public enum OrderType {
        ORDER,
        SALE,
        RESTOCK
    }

    public enum OrderStatus {
        PENDING,
        DELIVERED,
        CANCELLED,
        CREATED
    }

    private @Id
    @GeneratedValue Long id;
    private Date createdAt;

    private OrderStatus status;

    private Date updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<StockOrder> stockOrders = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    private OrderType type;

    public Order() {
        this.stockOrders = new HashSet<>();
    }

    public Order(
            Date createdAt,
            OrderStatus status,
            Date updatedAt,
            Set<StockOrder> stockOrders,
            Building building,
            OrderType type
    ) {
        this.createdAt = createdAt;
        this.status = status;
        this.updatedAt = updatedAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
