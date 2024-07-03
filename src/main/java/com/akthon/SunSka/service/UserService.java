package com.akthon.SunSka.service;

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
        System.out.println("plainPassword: " + plainPassword);
        String hashedPassword = passwordHasherService.hashPassword(plainPassword);
        System.out.println("hashedPassword: " + hashedPassword);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }
}
