package com.natesh.model;

import com.natesh.enums.AircraftStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false, length = 50)
    private String manufacturer;

    @Column(name = "rangeKm")
    private Integer rangeKm;

    @Column(name = "cruisingSpeedKmh")
    private Integer cruisingSpeedKmh;

    @Column(name = "maxAltitudeFt")
    private Integer maxAltitudeFt;

    @Column(nullable = false)
    private Integer seatingCapacity;

    @Column(name = "economy_seats")
    private Integer economySeats = 0;

    @Column(name = "business_seats")
    private Integer businessSeats = 0;

    @Column(name = "premium_seats")
    private Integer premiumSeats = 0;

    @Column(name = "first_class_seats")
    private Integer firstClassSeats = 0;

    @Column(name = "year_of_manufacture")
    private Integer yearOfManufacturing;

    private LocalDate registrationDate;
    private LocalDate nextMaintenanceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private AircraftStatus status = AircraftStatus.ACTIVE;

    private Boolean isAvailable = true;

    @ManyToOne
    private Airline airline;

    private Long currentAirportId;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @CreatedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Integer getTotalSeats() {
        return economySeats + premiumSeats + businessSeats + firstClassSeats;
    }

    public Boolean isOperational() {
        return AircraftStatus.ACTIVE.equals(status)
                && Boolean.TRUE.equals(isAvailable);
    }

    public Boolean requireMaintenance() {
        return nextMaintenanceDate != null
                && nextMaintenanceDate.isBefore(LocalDate.now().plusWeeks(2));
    }

}
