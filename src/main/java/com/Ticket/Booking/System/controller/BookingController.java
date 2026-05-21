package com.Ticket.Booking.System.controller;

import com.Ticket.Booking.System.dto.BookingRequest;
import com.Ticket.Booking.System.model.Booking;
import com.Ticket.Booking.System.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@Validated
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> book(@Valid @RequestBody BookingRequest req) {
        Booking booking = bookingService.bookSeat(req.getUserId(), req.getEventId(), req.getSeatId());
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancel(@PathVariable Long id) {
        Booking booking = bookingService.cancelBooking(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> historyByUser(@PathVariable Long userId) {
        List<Booking> list = bookingService.findByUserId(userId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Booking>> historyByEvent(@PathVariable Long eventId) {
        List<Booking> list = bookingService.findByEventId(eventId);
        return ResponseEntity.ok(list);
    }
}
