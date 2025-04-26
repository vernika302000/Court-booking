package com.badminton.repository;

import com.badminton.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCourtId(String courtId);
    List<Booking> findByUserId(String userId);
}
