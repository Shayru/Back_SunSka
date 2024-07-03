package com.akthon.SunSka.repository;

import com.akthon.SunSka.model.Building;
import com.akthon.SunSka.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EventRepository extends JpaRepository<Event, Long> {

}
