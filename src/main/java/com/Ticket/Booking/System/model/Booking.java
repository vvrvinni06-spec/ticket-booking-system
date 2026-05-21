package com.Ticket.Booking.System.model;

import com.Ticket.Booking.System.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private LocalDateTime bookingTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private BigDecimal amount;

    @PrePersist
    public void prePersist() {
        if (bookingTime == null) bookingTime = LocalDateTime.now();
    }
}
