package com.akthon.SunSka.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {

    private @Id
    @GeneratedValue Long id;

    private String login;

    private String password;

    private Boolean admin;
    private String role;

    @Column()
    private String name;

    @OneToMany(mappedBy = "user")
    private Set<Building> buildings;

    @OneToMany(mappedBy = "user")
    private Set<Sales> sales;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;


    public User() {
    }

    public User(
            String login,
            String password,
            Boolean admin,
            String role,
            String name,
            Set<Building> buildings,
            Set<Sales> sales,
            Set<Order> orders
    ) {
        this.login = login;
        this.password = password;
        this.admin = admin;
        this.role = role;
        this.name = name;
        this.buildings = buildings;
        this.sales = sales;
        this.orders = orders;
    }

    public Long getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return this.admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Set<Building> buildings) {
        this.buildings = buildings;
    }

    public Set<Sales> getSales() {
        return sales;
    }

    public void setSales(Set<Sales> sales) {
        this.sales = sales;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}