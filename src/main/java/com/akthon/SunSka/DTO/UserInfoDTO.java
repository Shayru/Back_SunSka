package com.akthon.SunSka.DTO;

public class UserInfoDTO {

    public Long id;
    public String login;
    public String name;
    public Boolean isAdmin;
    public Boolean isArchived;


    public UserInfoDTO() {
    }

    public UserInfoDTO(Long id, String login, String name, Boolean isAdmin, Boolean isArchived) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.isAdmin = isAdmin;
        this.isArchived = isArchived;
    }

}
