package com.akthon.SunSka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Partner {



    private @Id
    @GeneratedValue Long id;

    private String name;



    public Partner(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
