package com.natesh.service;

import com.natesh.model.Airport;
import com.natesh.payload.request.AirportRequest;
import com.natesh.payload.response.AirportResponse;

import java.util.List;

public interface AirportService {
    AirportResponse createAirport(AirportRequest airportRequest) throws Exception;

    AirportResponse getAirportById(Long id) throws Exception;

    List<AirportResponse> getAllAirports();

    AirportResponse updateAirport(Long id, AirportRequest airportRequest) throws Exception;

    void deleteAirport(Long id) throws Exception;

    List<AirportResponse> getAirportsByCityId(Long cityId);
}
