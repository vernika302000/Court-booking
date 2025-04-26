package com.badminton.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courtId;

    private String courtName;

    private double latitude;

    private double longitude;

    private double pricePerHour;

    private boolean isAvailable;

}
