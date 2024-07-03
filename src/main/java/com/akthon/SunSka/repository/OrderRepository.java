package com.akthon.SunSka.repository;

import com.akthon.SunSka.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {}
