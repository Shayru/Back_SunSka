package com.akthon.SunSka.repository;

import com.akthon.SunSka.DTO.BuildingInfoDTO;
import com.akthon.SunSka.DTO.StockResponseDTO;
import com.akthon.SunSka.model.Building;
import com.akthon.SunSka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findByType(Building.BuildingType type);
}
