package com.akthon.SunSka.controller;

import com.akthon.SunSka.DTO.UserUpdateDTO;
import com.akthon.SunSka.DTO.UserUpdatePasswordDTO;
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
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userData) {
        return userService.updateUser(id, userData);
    }

    @PutMapping("/{id}/password")
    public Optional<User> updateUserPassword(@PathVariable Long id, @RequestBody UserUpdatePasswordDTO userData) {
        return userService.updateUserPassword(id, userData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteuser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
