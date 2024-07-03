package com.akthon.SunSka.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Stock {

    private @Id
    @GeneratedValue Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "stock")
    private Set<StockOrder> stockOrders;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "stock")
    private Set<Sales> sales;

    private Integer initialStock;

    private Integer currentStock;

    private Integer warningAlert;

    public Stock() {
    }

    public Stock(
            Long id,
            Product product,
            Building building,
            Set<StockOrder> stockOrders,
            Event event,
            Integer initialStock,
            Integer currentStock,
            Integer warningAlert,
            Set<Sales> sales
    ) {
        this.id = id;
        this.product = product;
        this.building = building;
        this.stockOrders = stockOrders;
        this.event = event;
        this.initialStock = initialStock;
        this.currentStock = currentStock;
        this.warningAlert = warningAlert;
        this.sales = sales;
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
