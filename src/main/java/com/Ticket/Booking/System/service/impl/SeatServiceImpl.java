package com.Ticket.Booking.System.service.impl;

import com.Ticket.Booking.System.model.Seat;
import com.Ticket.Booking.System.model.enums.SeatStatus;
import com.Ticket.Booking.System.repository.SeatRepository;
import com.Ticket.Booking.System.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Override
    public Optional<Seat> findById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public List<Seat> findByEventId(Long eventId) {
        return seatRepository.findByEventId(eventId);
    }

    @Override
    public List<Seat> findAvailableSeatsByEvent(Long eventId) {
        return seatRepository.findByEventIdAndStatus(eventId, SeatStatus.AVAILABLE);
    }

    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }
}
