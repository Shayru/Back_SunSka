package com.akthon.SunSka.DTO;

public class UserAssociatedToBuildingDTO {
    public Long userId;
    public String userName;
    public Long buildingId;
    public boolean isAdmin;


    public UserAssociatedToBuildingDTO() {
    }

    public UserAssociatedToBuildingDTO(Long userId, String userName, Long buildingId, boolean isAdmin) {
        this.userId = userId;
        this.userName = userName;
        this.buildingId = buildingId;
        this.isAdmin = isAdmin;
    }
}
