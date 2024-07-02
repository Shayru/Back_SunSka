package com.akthon.SunSka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Partner {


    private @Id
    @GeneratedValue Long id;

    private String name;

    @OneToMany(mappedBy = "partner")
    private Set<Product> products;

    public Partner(
            Long id,
            String name,
            Set<Product> products
    ) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Partner() {
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
