package com.akthon.SunSka.service;

import com.akthon.SunSka.DTO.*;
import com.akthon.SunSka.enums.RoleEnum;
import com.akthon.SunSka.model.Building;
import com.akthon.SunSka.model.User;
import com.akthon.SunSka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public User createUser(UserCreateDTO userData) {
        if(userRepository.findByLogin(userData.login).isPresent()) {
            return null;
        }

        String plainPassword = userData.password;
        String hashedPassword = passwordHasherService.hashPassword(plainPassword);
        User user = new User(userData.login, hashedPassword, userData.admin, userData.name);
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

    public Optional<User> archiveUser(Long id) {
        return this.userRepository.findById(id).map(existingUser -> {
            existingUser.changeArchived();
            return userRepository.save(existingUser);
        });
    }

    public LoginResponseDTO login(LoginDTO loginData) {
        System.out.println("test");
        System.out.println(loginData);
        Optional<User> user = userRepository.findByLogin(loginData.login);
        if (user.isPresent() && passwordHasherService.verifyPassword(loginData.password, user.get().getPassword())) {
            User u = user.get();

            Boolean globalAdmin = this.isUserGlobalAdmin(u.getId());
            RoleEnum role = globalAdmin ? RoleEnum.GLOBALADMIN : u.getAdmin() ? RoleEnum.ADMIN : RoleEnum.USER;

            Building building = u.getBuildings().size() > 0 ? u.getBuildings().iterator().next() : null;

            BuildingInfoDTO buildingInfo = new BuildingInfoDTO(building.getId(), building.getName(), building.getType());


            return new LoginResponseDTO(u.getId(), u.getName(), u.getLogin(), role, buildingInfo);
        }
        return new LoginResponseDTO();
    }

    public Boolean isUserGlobalAdmin(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            Set<Building> buildings = user.get().getBuildings();
            // L'user est admin global s'il gère plus d'un building
            if(buildings.size() > 1) {
                return true;
            }
        }
        return false;
    }
}
