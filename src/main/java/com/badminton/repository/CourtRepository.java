package com.badminton.repository;

import com.badminton.model.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, Long> {
}
