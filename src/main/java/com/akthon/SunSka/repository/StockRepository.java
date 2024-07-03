package com.akthon.SunSka.repository;

import com.akthon.SunSka.model.Sales;
import com.akthon.SunSka.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    
}
