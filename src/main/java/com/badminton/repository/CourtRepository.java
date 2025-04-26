package com.badminton.repository;

import com.badminton.courtbooking.model.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, String> {
}
