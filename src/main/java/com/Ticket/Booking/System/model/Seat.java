package com.Ticket.Booking.System.model;

import com.Ticket.Booking.System.model.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    private String seatNumber; // e.g., A1, B2

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private BigDecimal price;

}
