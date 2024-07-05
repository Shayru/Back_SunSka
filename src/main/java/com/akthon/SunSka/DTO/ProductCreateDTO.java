package com.akthon.SunSka.DTO;

public class ProductCreateDTO {
    public String name;
    public Long categoryId;
    public int capacity;
    public String unit;

    public ProductCreateDTO() {
    }

    public ProductCreateDTO(String name, Long categoryId, int capacity, String unit) {
        this.name = name;
        this.categoryId = categoryId;
        this.capacity = capacity;
        this.unit = unit;
    }
}

