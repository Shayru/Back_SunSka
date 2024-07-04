package com.akthon.SunSka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Stock {

    @Id
    @GeneratedValue
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "building_id")
    @JsonBackReference
    private Building building;

    @OneToMany(mappedBy = "stock")
    @JsonManagedReference
    private Set<StockOrder> stockOrders;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event;

    private Integer initialStock;

    private Integer currentStock;

    private Integer warningAlert;

    public Stock() {
    }

    public Stock(
            Product product,
            Building building,
            Set<StockOrder> stockOrders,
            Event event,
            Integer initialStock,
            Integer currentStock,
            Integer warningAlert
    ) {
        this.product = product;
        this.building = building;
        this.stockOrders = stockOrders;
        this.event = event;
        this.initialStock = initialStock;
        this.currentStock = currentStock;
        this.warningAlert = warningAlert;
    }


    public Integer getInitialStock() {
        return initialStock;
    }

    public void setInitialStock(Integer initialStock) {
        this.initialStock = initialStock;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    public Integer getWarningAlert() {
        return warningAlert;
    }

    public void setWarningAlert(Integer warningAlert) {
        this.warningAlert = warningAlert;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Set<StockOrder> getStockOrders() {
        return stockOrders;
    }

    public void setStockOrders(Set<StockOrder> stockOrders) {
        this.stockOrders = stockOrders;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
