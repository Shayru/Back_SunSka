package com.akthon.SunSka.DTO;

import com.akthon.SunSka.model.Building;

public class BuildingInfoDTO {

    public Long id;
    public String name;
    public Building.BuildingType type;

    public BuildingInfoDTO() {
    }

    public BuildingInfoDTO(Long id, String name, Building.BuildingType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
