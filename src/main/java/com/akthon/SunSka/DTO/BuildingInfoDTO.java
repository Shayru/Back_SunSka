package com.akthon.SunSka.DTO;

public class BuildingInfoDTO {

    public Long id;
    public String name;
    public String type;

    public BuildingInfoDTO() {
    }

    public BuildingInfoDTO(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
