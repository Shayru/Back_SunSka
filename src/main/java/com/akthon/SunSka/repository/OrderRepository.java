package com.akthon.SunSka.repository;

import com.akthon.SunSka.DTO.CategorySaleDTO;
import com.akthon.SunSka.DTO.OrderDetailDTO;
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

    @Query("SELECT NEW com.akthon.SunSka.DTO.OrderDetailDTO(o.id, o.createdAt, o.updatedAt, o.status) " +
            "FROM Order o " +
            "WHERE o.building.type = com.akthon.SunSka.model.Building.BuildingType.BAR " +
            "AND o.type = :orderType" +
            " AND o.building.id = :buildingId")
    List<OrderDetailDTO> findByBuildingIdAndTypeDetail(@Param("buildingId") Long buildingId,@Param("orderType") Order.OrderType orderType );


    List<Order> findByBuildingIdAndType(Long buildingId, Order.OrderType type);

    @Query("SELECT o " +
            "FROM Order o " +
            "WHERE o.building.type = com.akthon.SunSka.model.Building.BuildingType.BAR")
    List<Order> findAllTypeOfOrderByBar();

    @Query("SELECT NEW com.akthon.SunSka.DTO.CategorySaleDTO(s.product.name, SUM(so.quantity), MIN(o.id)) " +
            "FROM Order o JOIN o.stockOrders so JOIN so.stock s " +
            "WHERE o.type = :orderType AND s.product.category.id = :categoryId " +
            "GROUP BY s.product.name")
    List<CategorySaleDTO> findAllSalesByCategory(@Param("orderType") Order.OrderType orderType, @Param("categoryId") Long categoryId);



}
