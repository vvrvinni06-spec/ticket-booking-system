package com.Ticket.Booking.System.dto;

import com.Ticket.Booking.System.model.enums.SeatStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateSeatRequest {
    @NotBlank
    private String seatNumber;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal price;

    private SeatStatus status = SeatStatus.AVAILABLE;
}
