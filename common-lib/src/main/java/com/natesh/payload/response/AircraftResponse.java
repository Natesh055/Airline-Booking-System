package com.natesh.payload.response;

import com.natesh.enums.AircraftStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AircraftResponse {
    private Long id;
    private String code;
    private String model;
    private String manufacturer;
    private Integer rangeKm;
    private Integer cruisingSpeedKmh;
    private Integer maxAltitudeFt;
    private Integer seatingCapacity;
    private Integer economySeats;
    private Integer businessSeats;
    private Integer premiumSeats;
    private Integer firstClassSeats;
    private Integer yearOfManufacturing;
    private LocalDate registrationDate;
    private LocalDate nextMaintenanceDate;
    private AircraftStatus status;
    private Boolean isAvailable;


    private Long currentAirportId;
    private Long currentAirportCapacity;
    private String currentAirportCode;
    private String currentAirportName;

    private Long airlineId;
    private String airlineName;
    private String airlineIataCode;

    private Integer totalSeats;
    private Boolean requiredMantainance;
    private Boolean isOperational;


    private Instant createdAt;
    private Instant updatedAt;
}
