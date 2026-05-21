package com.Ticket.Booking.System.service;

import com.Ticket.Booking.System.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking bookSeat(Long userId, Long eventId, Long seatId);
    Booking cancelBooking(Long bookingId);
    Optional<Booking> findById(Long id);
    List<Booking> findByUserId(Long userId);
    List<Booking> findByEventId(Long eventId);
}
