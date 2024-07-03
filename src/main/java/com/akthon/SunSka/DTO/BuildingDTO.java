package com.akthon.SunSka.DTO;

import jakarta.persistence.Entity;

public class BuildingDTO {

    private String name;
    private String type;



    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
