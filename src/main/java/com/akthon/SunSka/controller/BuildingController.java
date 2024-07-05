package com.akthon.SunSka.controller;

import com.akthon.SunSka.DTO.*;
import com.akthon.SunSka.model.Building;
import com.akthon.SunSka.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping
    public List<BuildingInfoDTO> getAllBuildings() {
        return buildingService.getAllBuildings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Building> getBuildingById(@PathVariable Long id) {
        Optional<Building> building = buildingService.getBuildingById(id);
        return building.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/name")
    public String getBarName(@PathVariable Long id) {
        Optional<Building> building = buildingService.getBuildingById(id);
        return building.map(Building::getName).orElse(null);
    }

    @PostMapping
    public Building createBuilding(@RequestBody Building building) {
        return buildingService.createBuilding(building);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Building> updateBuilding(@PathVariable Long id, @RequestBody BuildingUpdateDTO buildingData) {
        Optional<Building> updatedBuilding = buildingService.updateBuilding(id, buildingData);
        return updatedBuilding.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{buildingId}/users/{userId}")
    public ResponseEntity<Building> addUserToBuilding(@PathVariable Long buildingId, @PathVariable Long userId) {
        Optional<Building> updatedBuilding = buildingService.addUserToBuilding(buildingId, userId);
        return updatedBuilding.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //TODO  Faire le remove user from building

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        boolean isDeleted = buildingService.deleteBuilding(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{year}/alerts")
    public List<BuildingAlertDTO> getBuildingsAndAlert(@PathVariable int year) {
        return buildingService.getBuildingsAndAlertForYear(year);
    }

    @GetMapping("/{year}/bars")
    public List<BuildingBarDTO> getBarsByYear(@PathVariable int year) {
        return buildingService.getBarsByYear(year);
    }

    @GetMapping("/{buildingId}/users")
    public List<UserAssociatedToBuildingDTO> getUserAssociatedToBuilding(@PathVariable Long buildingId) {
        return buildingService.getUserAssociatedToBuilding(buildingId);
    }

    @DeleteMapping("/{buildingId}/users/{userId}")
    public ResponseEntity<Building> deleteUserFromBuilding(@PathVariable Long buildingId, @PathVariable Long userId) {
        Optional<Building> updatedBuilding = buildingService.deleteUserFromBuilding(buildingId, userId);
        return updatedBuilding.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //TODO Faire une demande pour vérif si un utilisateur est dans le building
    //TODO Faire une demande pour vérif si un utilisateur est dans le building et est admin de celui ci
}
