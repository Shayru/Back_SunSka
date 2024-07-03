package com.akthon.SunSka.repository;

import com.akthon.SunSka.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
