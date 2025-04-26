package com.badminton.service;

import com.badminton.courtbooking.model.Court;
import com.badminton.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtService {

    @Autowired
    private CourtRepository courtRepository;

    public List<Court> getAllCourts() {
        return courtRepository.findAll();
    }
}
