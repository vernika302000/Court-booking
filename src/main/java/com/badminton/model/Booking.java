package com.badminton.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    private String bookingId;
    private String userId;
    private String courtId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String bookingStatus;
}
