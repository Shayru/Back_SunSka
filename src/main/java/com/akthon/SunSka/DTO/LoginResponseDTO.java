package com.akthon.SunSka.DTO;

public class LoginResponseDTO {

    public Long id;
    public String login;
    public String name;


    public LoginResponseDTO() {
    }

    public LoginResponseDTO(Long id, String login, String name) {
        this.id = id;
        this.login = login;
        this.name = name;
    }

}
