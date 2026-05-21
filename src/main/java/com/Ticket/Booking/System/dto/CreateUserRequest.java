package com.Ticket.Booking.System.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank
    private String firstName;

    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String phone;
}
