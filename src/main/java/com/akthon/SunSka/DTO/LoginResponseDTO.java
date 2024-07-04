package com.akthon.SunSka.DTO;

import com.akthon.SunSka.enums.RoleEnum;
import com.akthon.SunSka.model.Building;

public class LoginResponseDTO {

    public Long id;
    public String login;
    public String name;
    public RoleEnum role;
    public BuildingInfoDTO building;


    public LoginResponseDTO() {
    }

    public LoginResponseDTO(Long id, String login, String name, RoleEnum role, BuildingInfoDTO building) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.role = role;
        this.building = building;
    }

}
