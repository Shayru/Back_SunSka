package com.akthon.SunSka.DTO;

public class BuildingAlertDTO {
    private Long id;
    private String barName;
    private boolean isAlert;


    public BuildingAlertDTO() {
    }

    public BuildingAlertDTO(Long id, String barName, boolean isAlert) {
        this.barName = barName;
        this.isAlert = isAlert;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBarName() {
        return barName;
    }

    public boolean isAlert() {
        return isAlert;
    }
}
