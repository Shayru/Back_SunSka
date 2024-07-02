package com.akthon.SunSka.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Product {

    private @Id
    @GeneratedValue Long id;

    private String name;

    private Integer capacity;

    private String unit;

    private String unitCase;

    public Product() {}

    public Product(
            String name,
            Integer capacity,
            String unit,
            String unitCase
            ) {

        this.name = name;
        this.capacity = capacity;
        this.unit = unit;
        this.unitCase = unitCase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitCase() {
        return unitCase;
    }

    public void setUnitCase(String unitCase) {
        this.unitCase = unitCase;
    }
}
