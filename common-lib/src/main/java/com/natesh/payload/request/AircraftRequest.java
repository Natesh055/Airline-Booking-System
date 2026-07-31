package com.natesh.payload.request;

import com.natesh.enums.AircraftStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class  AircraftRequest {
    @NotBlank(message = "Aircraft code is required.")
    private String code;

    @NotBlank(message = "Aircraft model is required.")
    private String model;

    @NotBlank(message = "Aircraft manufacturer is required.")
    private String manufacturer;

    @NotNull(message = "Seating capacity is required")
    @Positive(message = "Seating capacity must be positive")
    private Integer seatingCapacity;

    @Positive(message = "economy_seats must be positive. ")
    private Integer economySeats;

    @Positive(message = "business_seats must be positive. ")
    private Integer businessSeats;

    @Positive(message = "premium_seats must be positive. ")
    private Integer premiumSeats;

    @Positive(message = "first_class_seats must be positive. ")
    private Integer firstClassSeats;

    @Positive(message = "rangeKm must be positive. ")
    private Integer rangeKm;

    @Positive(message = "cruisingSpeedKmh must be positive. ")
    private Integer cruisingSpeedKmh;

    @Positive(message = "maxAltitudeFt must be positive. ")
    private Integer maxAltitudeFt;


    @Positive(message = "yearOfManufacturing must be positive. ")
    private Integer yearOfManufacturing;

    private LocalDate registrationDate;
    private LocalDate nextMaintenanceDate;

    @NotNull(message = "Status is required")
    private AircraftStatus status ;

    @NotNull(message = "Availability Status is required")
    private Boolean isAvailable = true;

    private Long currentAirportId;

}
