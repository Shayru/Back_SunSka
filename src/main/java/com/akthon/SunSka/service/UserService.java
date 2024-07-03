package com.akthon.SunSka.service;

import com.akthon.SunSka.DTO.UserUpdateDTO;
import com.akthon.SunSka.DTO.UserUpdatePasswordDTO;
import com.akthon.SunSka.model.User;
import com.akthon.SunSka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordHasherService passwordHasherService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        String plainPassword = user.getPassword();
        String hashedPassword = passwordHasherService.hashPassword(plainPassword);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id, UserUpdateDTO userData) {
        return this.userRepository.findById(id).map(existingUser-> {
            existingUser.setName(userData.name);
            existingUser.setAdmin(userData.admin);
            existingUser.setLogin(userData.login);
            return userRepository.save(existingUser);
        });
    }

    public Optional<User> updateUserPassword(Long id, UserUpdatePasswordDTO userData) {
        return this.userRepository.findById(id).map(existingUser-> {
            String plainPassword = userData.password;
            String hashedPassword = passwordHasherService.hashPassword(plainPassword);
            existingUser.setPassword(hashedPassword);
            return userRepository.save(existingUser);
        });
    }

    public boolean deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }
}
