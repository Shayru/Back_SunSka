package com.akthon.SunSka.DTO;

import jakarta.persistence.Column;

public class ProductDTO {
    private String name;

    private Integer capacity;

    private String unit;

    @Column()
    private String unitCase;

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
