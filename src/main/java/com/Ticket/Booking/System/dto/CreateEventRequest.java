package com.Ticket.Booking.System.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateEventRequest {
    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String venue;

    @NotNull
    @Future
    private LocalDateTime startTime;

    @NotNull
    @Future
    private LocalDateTime endTime;

    @NotNull
    @Positive
    private Integer totalSeats;

    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal basePrice;
}
