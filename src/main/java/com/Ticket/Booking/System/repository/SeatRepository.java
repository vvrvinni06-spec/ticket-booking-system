package com.Ticket.Booking.System.repository;

import com.Ticket.Booking.System.model.Seat;
import com.Ticket.Booking.System.model.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByEventId(Long eventId);
    List<Seat> findByEventIdAndStatus(Long eventId, SeatStatus status);
}
