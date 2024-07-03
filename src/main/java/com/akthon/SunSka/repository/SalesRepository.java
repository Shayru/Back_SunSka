package com.akthon.SunSka.repository;

import com.akthon.SunSka.model.ProductCategory;
import com.akthon.SunSka.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    
}
