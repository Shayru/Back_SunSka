package com.akthon.SunSka.service;

import com.akthon.SunSka.DTO.EventUpdateDTO;
import com.akthon.SunSka.model.Event;
import com.akthon.SunSka.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PasswordHasherService passwordHasherService;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Optional<Event> updateEvent(Long id, EventUpdateDTO eventData) {
        return this.eventRepository.findById(id).map(existingEvent-> {
            existingEvent.setYear(eventData.year);
            existingEvent.setStartDate(eventData.startDate);
            existingEvent.setEndDate(eventData.endDate);
            return eventRepository.save(existingEvent);
        });
    }

    public boolean deleteEvent(Long id) {
        return eventRepository.findById(id).map(event -> {
            eventRepository.delete(event);
            return true;
        }).orElse(false);
    }
}
