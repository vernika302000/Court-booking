package com.badminton.service;

import com.badminton.model.Booking;
import com.badminton.model.Court;
import com.badminton.repository.BookingRepository;
import com.badminton.repository.CourtRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourtService {

    @Autowired
    private CourtRepository courtRepository;

    @Autowired
    private BookingRepository bookingRepository;



    // Get all courts
    public List<Court> getAllCourts() {
        return courtRepository.findAll();
    }

    // Get a court by ID
    public Court getCourtById(String courtId) {
        return courtRepository.findById(Long.valueOf(courtId))
                .orElse(null);
    }


    // Add a new court
    public Court addCourt(Court court) {
        return courtRepository.save(court);
    }

    public Court updateCourt(String courtId, Court courtDetails) {
        Court existingCourt = courtRepository.findById(Long.valueOf(courtId))
                .orElseThrow(() -> new RuntimeException("Court not found"));
        existingCourt.setCourtName(courtDetails.getCourtName());
        existingCourt.setLatitude(courtDetails.getLatitude());
        existingCourt.setLongitude(courtDetails.getLongitude());
        existingCourt.setPricePerHour(courtDetails.getPricePerHour());
        return courtRepository.save(existingCourt);
    }

    // Delete a court by courtId
    public void deleteCourt(String courtId) {
        Court existingCourt = courtRepository.findById(Long.valueOf(courtId))
                .orElseThrow(() -> new RuntimeException("Court not found"));

        courtRepository.delete(existingCourt);
    }

    // Get all bookings for a specific court
    public List<Booking> getBookingsForCourt(String courtId) {
        return bookingRepository.findByCourtId(courtId);
    }
}
