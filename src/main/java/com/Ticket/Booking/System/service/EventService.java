package com.Ticket.Booking.System.service;

import com.Ticket.Booking.System.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Optional<Event> findById(Long id);
    List<Event> findAll();
    Event save(Event event);
}
