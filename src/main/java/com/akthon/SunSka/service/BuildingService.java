package com.akthon.SunSka.service;

import com.akthon.SunSka.DTO.*;
import com.akthon.SunSka.model.Building;
import com.akthon.SunSka.model.User;
import com.akthon.SunSka.repository.BuildingRepository;
import com.akthon.SunSka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StockService stockService;

    public List<BuildingInfoDTO> getAllBuildings() {
        List<Building> buildings = buildingRepository.findAll();
        return buildings.stream()
                .map(building -> new BuildingInfoDTO(building.getId(), building.getName(), building.getType()))
                .collect(Collectors.toList());
    }

    public Optional<Building> getBuildingById(Long id) {
        return buildingRepository.findById(id);
    }

    public Building createBuilding(Building building) {
        return buildingRepository.save(building);
    }

    public Optional<Building> updateBuilding(Long id, BuildingUpdateDTO buildingData) {
        System.out.println(buildingData);
        return buildingRepository.findById(id).map(existingBuilding -> {
            existingBuilding.setName(buildingData.name);
            existingBuilding.setType(buildingData.type);
            return buildingRepository.save(existingBuilding);
        });
    }

    public Optional<Building> addUserToBuilding(Long buildingId, Long userId) {
        Optional<Building> building = buildingRepository.findById(buildingId);
        Optional<User> user = userRepository.findById(userId);

        if (building.isPresent() && user.isPresent()) {
            Building existingBuilding = building.get();
            User existingUser = user.get();

            existingBuilding.getUsers().add(existingUser);
            existingUser.getBuildings().add(existingBuilding);

            buildingRepository.save(existingBuilding);
            userRepository.save(existingUser);

            return Optional.of(existingBuilding);
        }

        return Optional.empty();
    }

    public boolean deleteBuilding(Long id) {
        return buildingRepository.findById(id).map(building -> {
            buildingRepository.delete(building);
            return true;
        }).orElse(false);
    }

    public List<BuildingAlertDTO> getBuildingsAndAlertForYear(int year) {
        List<BuildingAlertDTO> buildingsAlerts = new java.util.ArrayList<>(List.of());
        //On ne récup que les bars
        List<Building> buildings = buildingRepository.findByType(Building.BuildingType.BAR);

        buildings.forEach(building ->  {
            List<Long> alerts = stockService.findStockInAlertForBarAndYear(building.getId(), year);
            if(!alerts.isEmpty()) {
                BuildingAlertDTO buildingAlert = new BuildingAlertDTO(building.getId(), building.getName(), true);
                buildingsAlerts.add(buildingAlert);
            } else{
                BuildingAlertDTO buildingAlert = new BuildingAlertDTO(building.getId(), building.getName(), false);
                buildingsAlerts.add(buildingAlert);
            }
        });

        return  buildingsAlerts;
    }

    public List<BuildingBarDTO> getBarsByYear(int year) {
        List<BuildingBarDTO> bars = new java.util.ArrayList<>(List.of());
        //On ne récup que les bars
        List<Building> buildings = buildingRepository.findByType(Building.BuildingType.BAR);

        buildings.forEach(building ->  {
            BuildingBarDTO bar = new BuildingBarDTO(building.getId(), building.getName());
            bars.add(bar);
        });

        return  bars;
    }

    public List<UserAssociatedToBuildingDTO> getUserAssociatedToBuilding(Long buildingId) {
        List<User> users = userRepository.findByBuildingId(buildingId);
        return users.stream()
                .map(user -> new UserAssociatedToBuildingDTO(user.getId(), user.getName(), buildingId, user.getAdmin()))
                .collect(Collectors.toList());
    }

    public Optional<Building> deleteUserFromBuilding(Long buildingId, Long userId) {
        Optional<Building> buildingOptional = buildingRepository.findById(buildingId);
        if (buildingOptional.isPresent()) {
            Building building = buildingOptional.get();
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                building.getUsers().remove(user);
                user.getBuildings().remove(building);
                buildingRepository.save(building);
                return Optional.of(building);
            }
        }
        return Optional.empty();
    }

}
