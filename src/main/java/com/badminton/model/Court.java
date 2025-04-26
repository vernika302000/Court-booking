package com.badminton.courtbooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Court {
    @Id
    private String courtId;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private double pricePerHour;
}
