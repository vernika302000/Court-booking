package com.badminton.controller;

import com.badminton.model.Booking;
import com.badminton.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking bookCourt(@RequestParam String userId,
                             @RequestParam String courtId,
                             @RequestParam String startTime,
                             @RequestParam String endTime) {

        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);
        return bookingService.bookCourt(userId, courtId, start, end);
    }
}
