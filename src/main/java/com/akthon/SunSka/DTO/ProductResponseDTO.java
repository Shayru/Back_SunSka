package com.akthon.SunSka.DTO;

import jakarta.persistence.GeneratedValue;

import java.util.Date;
import java.util.List;

public class ProductResponseDTO {
    public Long id;
    public String name;
    public Integer capacity;
    public String unit;
    public boolean isActif;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Long id, String name, Integer capacity, String unit, Boolean isActif) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.unit = unit;
        this.isActif = isActif;
    }
}

