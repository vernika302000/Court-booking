package com.badminton.controller;

import com.badminton.model.Booking;
import com.badminton.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // API to book a court
    @PostMapping
    public Booking bookCourt(@RequestParam String userId,
                             @RequestParam String courtId,
                             @RequestParam String startTime,
                             @RequestParam String endTime) {

        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);

        if (!bookingService.isCourtAvailable(courtId, start, end)) {
            throw new RuntimeException("Court is already booked for this time slot!");
        }

        return bookingService.bookCourt(userId, courtId, start.toString(), end.toString());
    }

    // API to retrieve all bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // API to retrieve a specific booking by bookingId
    @GetMapping("/{bookingId}")
    public Booking getBookingById(@PathVariable String bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    // API to cancel a booking
    @DeleteMapping("/{bookingId}")
    public void cancelBooking(@PathVariable String bookingId) {
        bookingService.cancelBooking(bookingId);
    }

    // API to get all bookings for a specific court
    @GetMapping("/courts/{courtId}")
    public List<Booking> getBookingsByCourt(@PathVariable String courtId) {
        return bookingService.getBookingsByCourt(courtId);
    }

    // API to get all bookings by a specific user
    @GetMapping("/users/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable String userId) {
        return bookingService.getBookingsByUser(userId);
    }

    // API to update booking
    @PutMapping("/{bookingId}")
    public Booking updateBooking(@PathVariable String bookingId,
                                 @RequestBody Booking bookingDetails) {
        return bookingService.updateBooking(bookingId, bookingDetails);
    }
}
