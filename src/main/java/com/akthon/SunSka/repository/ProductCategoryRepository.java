package com.akthon.SunSka.repository;

import com.akthon.SunSka.model.Product;
import com.akthon.SunSka.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}

