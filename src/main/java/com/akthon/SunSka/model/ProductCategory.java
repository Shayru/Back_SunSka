package com.akthon.SunSka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class ProductCategory {


    private @Id
    @GeneratedValue Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public ProductCategory() {
    }

    public ProductCategory(
            String name
    ) {
        this.name = name;
        this.products = Set.of();
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
