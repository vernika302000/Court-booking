package com.badminton.controller;

import com.badminton.courtbooking.model.Court;
import com.badminton.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courts")
public class CourtController {

    @Autowired
    private CourtService courtService;

    @GetMapping
    public List<Court> getAllCourts() {
        return courtService.getAllCourts();
    }
}
