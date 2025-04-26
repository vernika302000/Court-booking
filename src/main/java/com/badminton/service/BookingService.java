package com.badminton.service;

import com.badminton.model.Booking;
import com.badminton.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking bookCourt(String userId, String courtId, LocalDateTime startTime, LocalDateTime endTime) {
        // Check availability
        List<Booking> overlappingBookings = bookingRepository.findByCourtIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                courtId, endTime, startTime
        );

        if (!overlappingBookings.isEmpty()) {
            throw new RuntimeException("Court already booked for this time slot!");
        }

        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setUserId(userId);
        booking.setCourtId(courtId);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setBookingStatus("CONFIRMED");

        return bookingRepository.save(booking);
    }
}
