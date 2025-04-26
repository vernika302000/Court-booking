package com.badminton.service;

import com.badminton.model.Booking;
import com.badminton.model.Court;
import com.badminton.repository.BookingRepository;
import com.badminton.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CourtRepository courtRepository;

    // Book a court
    public Booking bookCourt(String userId, String courtId, String startTime, String endTime) {
        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);

        if (!isCourtAvailable(courtId, start, end)) {
            throw new RuntimeException("Court already booked for this time slot!");
        }

        // Save the booking first
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setCourtId(courtId);
        booking.setStartTime(start);
        booking.setEndTime(end);
        booking.setBookingStatus("CONFIRMED");

        bookingRepository.save(booking);

        // Update court availability after booking
        Court court = courtRepository.findById(Long.valueOf(courtId))
                .orElseThrow(() -> new RuntimeException("Court not found"));

        court.setAvailable(false); // Mark court as unavailable
        courtRepository.save(court); // Update the court status

        return booking;
    }


    // Check if court is available
    public boolean isCourtAvailable(String courtId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Booking> existingBookings = bookingRepository.findByCourtId(courtId);

        for (Booking booking : existingBookings) {
            if (startTime.isBefore(booking.getEndTime()) && endTime.isAfter(booking.getStartTime())) {
                return false; // Overlapping slot
            }
        }
        return true;
    }

    public List<Booking> getBookingsByCourt(String courtId)
    {
        return bookingRepository.findByCourtId(courtId);
    }

    // Get booking details by bookingId
    public Booking getBookingById(String bookingId) {
        return bookingRepository.findById(Long.valueOf(bookingId)).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    // Cancel a booking by bookingId
    public void cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(Long.valueOf(bookingId))
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        bookingRepository.delete(booking);
    }

    // Get all bookings by userId
    public List<Booking> getBookingsByUser(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    // Update booking details
    public Booking updateBooking(String bookingId, Booking updatedBooking) {
        Booking existingBooking = bookingRepository.findById(Long.valueOf(bookingId))
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        existingBooking.setStartTime(updatedBooking.getStartTime());
        existingBooking.setEndTime(updatedBooking.getEndTime());
        existingBooking.setBookingStatus(updatedBooking.getBookingStatus());
        return bookingRepository.save(existingBooking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
