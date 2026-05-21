package com.Ticket.Booking.System.repository;

import com.Ticket.Booking.System.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
