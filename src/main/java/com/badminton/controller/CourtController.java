package com.badminton.controller;

import com.badminton.model.Booking;
import com.badminton.model.Court;
import com.badminton.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courts")
public class CourtController {

    @Autowired
    private CourtService courtService;

    // Get all courts
    @GetMapping
    public List<Court> getAllCourts() {
        return courtService.getAllCourts();
    }

    // Get details of a specific court by courtId
    @GetMapping("/{courtId}")
    public Court getCourt(@PathVariable String courtId) {
        return courtService.getCourtById(courtId);
    }

    // Add a new court
    @PostMapping
    public Court addCourt(@RequestBody Court court) {
        return courtService.addCourt(court);
    }

    // Update an existing court's details
    @PutMapping("/{courtId}")
    public Court updateCourt(@PathVariable String courtId, @RequestBody Court courtDetails) {
        return courtService.updateCourt(courtId, courtDetails);
    }

    // Delete a court by courtId
    @DeleteMapping("/{courtId}")
    public void deleteCourt(@PathVariable String courtId) {
        courtService.deleteCourt(courtId);
    }

    // Get all bookings for a specific court by courtId
    @GetMapping("/{courtId}/bookings")
    public List<Booking> getBookingsForCourt(@PathVariable String courtId) {
        return courtService.getBookingsForCourt(courtId);
    }
}
