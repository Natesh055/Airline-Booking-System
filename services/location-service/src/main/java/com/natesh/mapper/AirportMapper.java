package com.natesh.mapper;

import com.natesh.model.Airport;
import com.natesh.payload.request.AirportRequest;
import com.natesh.payload.response.AirportResponse;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper {
    public static Airport toEntity(AirportRequest airportRequest) {
        if (airportRequest == null)
            return null;
        return Airport.builder()
                .iataCode(airportRequest.getIataCode())
                .name(airportRequest.getName())
                .timeZone(airportRequest.getTimeZone())
                .timeZone(airportRequest.getTimeZone())
                .address(airportRequest.getAddress())
                .geoCode(airportRequest.getGeoCode())
                .build();
    }

    public static AirportResponse toResponse(Airport airport) {
        if (airport == null) {
            return null;
        }
        return AirportResponse.builder()
                .id(airport.getId())
                .iataCode(airport.getIataCode())
                .name(airport.getName())
//                .timeZone(airport.getTimeZone())
                .address(airport.getAddress())
                .city(CityMapper.toResponse(airport.getCity()))
                .geoCode(airport.getGeoCode())
                .build();
    }

    public static void updateEntity(AirportRequest request, Airport existingAirport) {
        if (request == null || existingAirport == null)
            return;

        if (request.getName() != null)
            existingAirport.setName(request.getName());

        if (request.getAddress() != null)
            existingAirport.setAddress(request.getAddress());

        if (request.getIataCode() != null)
            existingAirport.setIataCode(request.getIataCode());

        if (request.getGeoCode() != null)
            existingAirport.setGeoCode(request.getGeoCode());
    }
}
