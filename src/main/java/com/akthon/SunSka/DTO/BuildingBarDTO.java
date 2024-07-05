package com.akthon.SunSka.DTO;

public class BuildingBarDTO {
    private Long id;
    private String barName;


    public BuildingBarDTO() {
    }

    public BuildingBarDTO(Long id, String barName) {
        this.barName = barName;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBarName() {
        return barName;
    }
}
