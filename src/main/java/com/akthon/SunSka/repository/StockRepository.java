package com.akthon.SunSka.repository;

import com.akthon.SunSka.model.Stock;
import com.akthon.SunSka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
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
}
