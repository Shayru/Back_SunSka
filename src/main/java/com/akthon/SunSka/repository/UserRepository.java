package com.akthon.SunSka.repository;

import com.akthon.SunSka.DTO.UserInfoDTO;
import com.akthon.SunSka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    @Query("SELECT NEW com.akthon.SunSka.DTO.UserInfoDTO( u.id, u.login, u.name, u.admin, u.isArchived ) FROM User u ")
    List<UserInfoDTO> findAllUsersInfo();
}
