package com.akthon.SunSka.model;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Product {

    private @Id
    @GeneratedValue Long id;

    private String name;

    private Integer capacity;

    private String unit;

    @Column()
    private String unitCase;

    @OneToMany(mappedBy = "product")
    private Set<Stock> stocks;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;


    public Product() {
    }

    public Product(
            String name,
            Integer capacity,
            String unit,
            String unitCase,
            Set<Stock> stocks,
            ProductCategory category,
            Partner partner
    ) {

        this.name = name;
        this.capacity = capacity;
        this.unit = unit;
        this.unitCase = unitCase;
        this.stocks = stocks;
        this.category = category;
        this.partner = partner;
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

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }
}
