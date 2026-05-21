package com.Ticket.Booking.System.service.impl;

import com.Ticket.Booking.System.exception.BadRequestException;
import com.Ticket.Booking.System.exception.ResourceNotFoundException;
import com.Ticket.Booking.System.model.Booking;
import com.Ticket.Booking.System.model.Event;
import com.Ticket.Booking.System.model.Seat;
import com.Ticket.Booking.System.model.User;
import com.Ticket.Booking.System.model.enums.BookingStatus;
import com.Ticket.Booking.System.model.enums.SeatStatus;
import com.Ticket.Booking.System.repository.BookingRepository;
import com.Ticket.Booking.System.repository.EventRepository;
import com.Ticket.Booking.System.repository.SeatRepository;
import com.Ticket.Booking.System.repository.UserRepository;
import com.Ticket.Booking.System.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final SeatRepository seatRepository;

    @Override
    @Transactional
    public Booking bookSeat(Long userId, Long eventId, Long seatId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found: " + eventId));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found: " + seatId));

        if (!seat.getEvent().getId().equals(event.getId())) {
            throw new BadRequestException("Seat does not belong to the specified event");
        }

        if (seat.getStatus() != SeatStatus.AVAILABLE) {
            throw new BadRequestException("Seat is not available");
        }

        // mark seat booked
        seat.setStatus(SeatStatus.BOOKED);
        seatRepository.save(seat);

        BigDecimal amount = seat.getPrice() != null ? seat.getPrice() : event.getBasePrice();

        Booking booking = Booking.builder()
                .user(user)
                .event(event)
                .seat(seat)
                .status(BookingStatus.BOOKED)
                .amount(amount)
                .build();

        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + bookingId));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BadRequestException("Booking is already cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        Seat seat = booking.getSeat();
        seat.setStatus(SeatStatus.AVAILABLE);
        seatRepository.save(seat);

        return booking;
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> findByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public List<Booking> findByEventId(Long eventId) {
        return bookingRepository.findByEventId(eventId);
    }
}
