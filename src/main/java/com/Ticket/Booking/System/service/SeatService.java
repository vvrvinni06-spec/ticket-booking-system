package com.Ticket.Booking.System.service;

import com.Ticket.Booking.System.model.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatService {
    Optional<Seat> findById(Long id);
    List<Seat> findByEventId(Long eventId);
    List<Seat> findAvailableSeatsByEvent(Long eventId);
    Seat save(Seat seat);
}
