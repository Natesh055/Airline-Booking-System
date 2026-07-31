package com.natesh.mapper;

import com.natesh.model.Aircraft;
import com.natesh.model.Airline;
import com.natesh.payload.request.AircraftRequest;
import com.natesh.payload.response.AircraftResponse;
import org.springframework.stereotype.Component;

@Component

public class AircraftMapper {
    public static Aircraft toEntity(AircraftRequest request, Airline airline) {
        if (request == null) {
            return null;
        }

        return Aircraft.builder()
                .code(request.getCode())
                .model(request.getModel())
                .manufacturer(request.getManufacturer())
                .rangeKm(request.getRangeKm())
                .cruisingSpeedKmh(request.getCruisingSpeedKmh())
                .maxAltitudeFt(request.getMaxAltitudeFt())
                .seatingCapacity(request.getSeatingCapacity())
                .economySeats(request.getEconomySeats())
                .businessSeats(request.getBusinessSeats())
                .premiumSeats(request.getPremiumSeats())
                .firstClassSeats(request.getFirstClassSeats())
                .yearOfManufacturing(request.getYearOfManufacturing())
                .registrationDate(request.getRegistrationDate())
                .nextMaintenanceDate(request.getNextMaintenanceDate())
                .status(request.getStatus())
                .isAvailable(request.getIsAvailable())
                .airline(airline)
                .currentAirportId(request.getCurrentAirportId())
                .build();
    }

    public static AircraftResponse toResponse(Aircraft aircraft) {
        if (aircraft == null) {
            return null;
        }

        return AircraftResponse.builder()
                .id(aircraft.getId())
                .code(aircraft.getCode())
                .model(aircraft.getModel())
                .manufacturer(aircraft.getManufacturer())
                .rangeKm(aircraft.getRangeKm())
                .cruisingSpeedKmh(aircraft.getCruisingSpeedKmh())
                .maxAltitudeFt(aircraft.getMaxAltitudeFt())
                .seatingCapacity(aircraft.getSeatingCapacity())
                .economySeats(aircraft.getEconomySeats())
                .businessSeats(aircraft.getBusinessSeats())
                .premiumSeats(aircraft.getPremiumSeats())
                .firstClassSeats(aircraft.getFirstClassSeats())
                .yearOfManufacturing(aircraft.getYearOfManufacturing())
                .registrationDate(aircraft.getRegistrationDate())
                .nextMaintenanceDate(aircraft.getNextMaintenanceDate())
                .status(aircraft.getStatus())
                .isAvailable(aircraft.getIsAvailable())

                .airlineId(aircraft.getAirline() != null ? aircraft.getAirline().getId() : null)
                .airlineName(aircraft.getAirline() != null ? aircraft.getAirline().getName() : null)
                .airlineIataCode(aircraft.getAirline() != null ? aircraft.getAirline().getIataCode() : null)

                .currentAirportId(aircraft.getCurrentAirportId())
//                .currentAirportName(aircraft.getCurrentAirportName())
//                .currentAirportCapacity(aircraft.getAirline().getId())
//                .currentAirportCode(aircraft.getAirline().)
//                .curre

                .totalSeats(aircraft.getTotalSeats())
                .requiredMantainance(aircraft.requireMaintenance())
                .isOperational(aircraft.isOperational())

                .createdAt(aircraft.getCreatedAt())
                .updatedAt(aircraft.getUpdatedAt())
                .build();
    }

    public static void updateEntity(Aircraft aircraft, AircraftRequest request) {
        if (aircraft == null || request == null) {
            return;
        }

        aircraft.setCode(request.getCode());
        aircraft.setModel(request.getModel());
        aircraft.setManufacturer(request.getManufacturer());

        aircraft.setRangeKm(request.getRangeKm());
        aircraft.setCruisingSpeedKmh(request.getCruisingSpeedKmh());
        aircraft.setMaxAltitudeFt(request.getMaxAltitudeFt());

        aircraft.setSeatingCapacity(request.getSeatingCapacity());
        aircraft.setEconomySeats(request.getEconomySeats());
        aircraft.setBusinessSeats(request.getBusinessSeats());
        aircraft.setPremiumSeats(request.getPremiumSeats());
        aircraft.setFirstClassSeats(request.getFirstClassSeats());

        aircraft.setYearOfManufacturing(request.getYearOfManufacturing());
        aircraft.setRegistrationDate(request.getRegistrationDate());
        aircraft.setNextMaintenanceDate(request.getNextMaintenanceDate());

        aircraft.setStatus(request.getStatus());
        aircraft.setIsAvailable(request.getIsAvailable());

        aircraft.setCurrentAirportId(request.getCurrentAirportId());

    }
}
