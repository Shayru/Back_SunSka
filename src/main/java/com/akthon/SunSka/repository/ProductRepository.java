package com.akthon.SunSka.repository;

import com.akthon.SunSka.DTO.ProductResponseDTO;
import com.akthon.SunSka.DTO.StockResponseDTO;
import com.akthon.SunSka.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT NEW com.akthon.SunSka.DTO.ProductResponseDTO(p.id, p.name, p.capacity, p.unit, p.isActif) " +
            "FROM Product p ")
    List<ProductResponseDTO> findAllProducts();


}
