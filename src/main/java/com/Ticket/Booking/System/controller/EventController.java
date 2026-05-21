package com.Ticket.Booking.System.controller;

import com.Ticket.Booking.System.dto.CreateEventRequest;
import com.Ticket.Booking.System.model.Event;
import com.Ticket.Booking.System.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Validated
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Event> create(@Valid @RequestBody CreateEventRequest req) {
        if (req.getEndTime().isBefore(req.getStartTime()) || req.getEndTime().isEqual(req.getStartTime())) {
            throw new com.Ticket.Booking.System.exception.BadRequestException("Event end time must be after start time");
        }

        Event event = Event.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .venue(req.getVenue())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .totalSeats(req.getTotalSeats())
                .basePrice(req.getBasePrice())
                .build();

        Event saved = eventService.save(event);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Event>> listAll() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable Long id) {
        return eventService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
