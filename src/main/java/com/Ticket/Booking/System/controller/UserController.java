package com.Ticket.Booking.System.controller;

import com.Ticket.Booking.System.dto.CreateUserRequest;
import com.Ticket.Booking.System.model.User;
import com.Ticket.Booking.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> register(@Valid @RequestBody CreateUserRequest req) {
        userService.findByEmail(req.getEmail()).ifPresent(existing -> {
            throw new com.Ticket.Booking.System.exception.BadRequestException("Email is already registered");
        });

        User user = User.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .email(req.getEmail())
                .password(req.getPassword())
                .phone(req.getPhone())
                .role("USER")
                .build();

        User saved = userService.save(user);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
