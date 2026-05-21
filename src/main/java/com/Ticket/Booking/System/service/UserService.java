package com.Ticket.Booking.System.service;

import com.Ticket.Booking.System.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    User save(User user);
}
