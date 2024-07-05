package com.akthon.SunSka.repository;

import com.akthon.SunSka.DTO.ShopResponseDTO;
import com.akthon.SunSka.DTO.StockResponseDTO;
import com.akthon.SunSka.model.Stock;
import com.akthon.SunSka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT NEW com.akthon.SunSka.DTO.StockResponseDTO(s.currentStock, s.product.name, s.product.capacity, s.product.unit, s.product.id, s.id, s.warningAlert) " +
            "FROM Stock s " +
            "INNER JOIN Product p " +
            "ON p.id = s.product.id " +
            "WHERE s.event.year = :year " +
            "AND s.building.id = :idBar ")
    List<StockResponseDTO> findStockForBuildingByYear(@Param("year") int year, @Param("idBar") Long idBar);

    @Query("SELECT s.id " +
            "FROM Stock s " +
            "WHERE s.currentStock <= s.warningAlert " +
            "AND s.event.year = :year")
    List<Long> findStockInAlertForYear(@Param("year") int year);

    @Query("SELECT s.id " +
            "FROM Stock s " +
            "WHERE s.currentStock < s.warningAlert " +
            "AND s.event.year = :year " +
            "AND s.building.id = :idBar")
    List<Long> findStockInAlertForBarAndYear(@Param("idBar") Long idBar, @Param("year") int year);

    @Query("SELECT NEW com.akthon.SunSka.DTO.ShopResponseDTO(s.building.id, s.building.name) " +
            "FROM Stock s " +
            "WHERE s.event.year = :year " +
            "AND s.building.type =  com.akthon.SunSka.model.Building.BuildingType.SHOP")
    ShopResponseDTO findShopByYear(@Param("year") int year);
}
