package com.akthon.SunSka.model;

import jakarta.persistence.Entity;

@Entity
public class Stock {

    private Integer initialStock;

    private Integer currentStock;

    private Integer warningAlert;

    public Stock() {
    }

    public Stock(Long id, Integer initialStock, Integer currentStock, Integer warningAlert) {
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
}
