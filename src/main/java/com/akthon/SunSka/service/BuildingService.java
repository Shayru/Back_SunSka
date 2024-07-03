package com.akthon.SunSka.service;

import com.akthon.SunSka.DTO.BuildingDTO;
import com.akthon.SunSka.model.Building;
import com.akthon.SunSka.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    public Optional<Building> getBuildingById(Long id) {
        return buildingRepository.findById(id);
    }

    public Building createBuilding(Building building) {
        return buildingRepository.save(building);
    }

    public Optional<Building> updateBuilding(Long id, BuildingDTO buildingDTO) {
        return buildingRepository.findById(id).map(existingBuilding -> {
            existingBuilding.setName(buildingDTO.getName());
            existingBuilding.setType(buildingDTO.getType());
            return buildingRepository.save(existingBuilding);
        });
    }

    public boolean deleteBuilding(Long id) {
        return buildingRepository.findById(id).map(building -> {
            buildingRepository.delete(building);
            return true;
        }).orElse(false);
    }

}
