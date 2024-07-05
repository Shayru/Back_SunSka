package com.akthon.SunSka.model;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class  Product {

    private @Id
    @GeneratedValue Long id;

    private String name;

    private Integer capacity;

    private String unit;

    private Boolean isActif;

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
            ProductCategory category
    ) {

        this.name = name;
        this.capacity = capacity;
        this.unit = unit;
        this.stocks = Set.of();
        this.category = category;
        this.partner = null;
        this.isActif = true;
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

    public Boolean isActif() {
        return isActif;
    }

    public void changeActif() {
        this.isActif = !this.isActif;
    }
}
