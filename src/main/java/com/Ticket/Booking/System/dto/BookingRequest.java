package com.Ticket.Booking.System.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Long eventId;

    @NotNull
    private Long seatId;
}
