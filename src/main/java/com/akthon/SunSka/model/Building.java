package com.akthon.SunSka.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Building {

    private @Id
    @GeneratedValue Long id;

    private String name;

    private String type;

    @OneToMany(mappedBy = "building")
    private Set<Stock> stocks;

    @ManyToMany(mappedBy = "buildings")
    private Set<User> users = new HashSet<>();;

    public Building() {
    }


    public Building(
            Long id,
            String name,
            String type,
            Set<Stock> stocks,
            Set<User> users
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.stocks = stocks;
        this.users = users;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
