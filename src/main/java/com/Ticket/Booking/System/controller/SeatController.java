package com.Ticket.Booking.System.controller;

import com.Ticket.Booking.System.dto.CreateSeatRequest;
import com.Ticket.Booking.System.exception.BadRequestException;
import com.Ticket.Booking.System.exception.ResourceNotFoundException;
import com.Ticket.Booking.System.model.Event;
import com.Ticket.Booking.System.model.Seat;
import com.Ticket.Booking.System.model.enums.SeatStatus;
import com.Ticket.Booking.System.repository.EventRepository;
import com.Ticket.Booking.System.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/events/{eventId}/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;
    private final EventRepository eventRepository;

    @GetMapping("/available")
    public ResponseEntity<List<Seat>> available(@PathVariable Long eventId) {
        List<Seat> seats = seatService.findAvailableSeatsByEvent(eventId);
        return ResponseEntity.ok(seats);
    }

    @GetMapping
    public ResponseEntity<List<Seat>> all(@PathVariable Long eventId) {
        return ResponseEntity.ok(seatService.findByEventId(eventId));
    }

    @PostMapping
    public ResponseEntity<Seat> createSeat(@PathVariable Long eventId,
                                           @Valid @RequestBody CreateSeatRequest request) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found: " + eventId));

        if (request.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException("Seat price must be zero or positive");
        }

        Seat seat = Seat.builder()
                .event(event)
                .seatNumber(request.getSeatNumber())
                .price(request.getPrice())
                .status(request.getStatus() != null ? request.getStatus() : SeatStatus.AVAILABLE)
                .build();
        return new ResponseEntity<>(seatService.save(seat), HttpStatus.CREATED);
    }
}
