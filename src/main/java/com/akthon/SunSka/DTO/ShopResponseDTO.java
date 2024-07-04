package com.akthon.SunSka.DTO;

public class ShopResponseDTO {
    public Long buildingId;

    public String name;

    public ShopResponseDTO() {
    }

    public ShopResponseDTO(Long buildingId, String name) {
        this.buildingId = buildingId;
        this.name = name;
    }
}
