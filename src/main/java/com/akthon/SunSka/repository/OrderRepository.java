package com.akthon.SunSka.repository;

import com.akthon.SunSka.model.Building;
import com.akthon.SunSka.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o.id " +
            "FROM Order o " +
            "WHERE o.building.id = :barId")
    List<Long> findByBuilding(@Param("barId") Long barId);
}
