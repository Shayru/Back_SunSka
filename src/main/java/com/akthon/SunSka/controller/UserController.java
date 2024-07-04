package com.akthon.SunSka.controller;

import com.akthon.SunSka.DTO.*;
import com.akthon.SunSka.model.Building;
import com.akthon.SunSka.model.User;
import com.akthon.SunSka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody UserCreateDTO userData) {
        return userService.createUser(userData);
    }

    @PutMapping("/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userData) {
        return userService.updateUser(id, userData);
    }

    @PutMapping("/{id}/password")
    public Optional<User> updateUserPassword(@PathVariable Long id, @RequestBody UserUpdatePasswordDTO userData) {
        return userService.updateUserPassword(id, userData);
    }

    @PutMapping("/{id}/archive")
    public Optional<User> archiveUser(@PathVariable Long id) {
        return userService.archiveUser(id);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO loginData) {
        System.out.println("test");
        return userService.login(loginData);
    }


    @GetMapping("/{id}/admin/global")
    public Boolean isUserGlobalAdmin(Long id) {
        return userService.isUserGlobalAdmin(id);
    }


    // TODO get all bars for a specific user
    // TODO get all admin bars for a specific user ?
}
