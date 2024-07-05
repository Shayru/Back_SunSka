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
    List<Long> findByBar(@Param("barId") Long barId);

    List<Order> findByBuildingIdAndType(Long buildingId, Order.OrderType type);

    @Query("SELECT o " +
            "FROM Order o " +
            "WHERE o.building.type = com.akthon.SunSka.model.Building.BuildingType.BAR")
    List<Order> findAllTypeOfOrderByBar();

    @Query("SELECT o FROM Order o JOIN o.stockOrders so JOIN so.stock s WHERE o.type = :orderType AND s.product.category.id = :categoryId")
    List<Order> findAllSalesByCategory(@Param("orderType") Order.OrderType orderType, @Param("categoryId") Long categoryId);

}
