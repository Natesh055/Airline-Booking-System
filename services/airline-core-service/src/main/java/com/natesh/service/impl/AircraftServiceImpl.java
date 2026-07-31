package com.natesh.service.impl;

import com.natesh.mapper.AircraftMapper;
import com.natesh.model.Aircraft;
import com.natesh.model.Airline;
import com.natesh.payload.request.AircraftRequest;
import com.natesh.payload.response.AircraftResponse;
import com.natesh.repository.AircraftRepository;
import com.natesh.repository.AirlineRepository;
import com.natesh.service.AircraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AircraftServiceImpl implements AircraftService {
    @Autowired
    AircraftRepository aircraftRepository;

    @Autowired
    AirlineRepository airlineRepository;

    @Override
    public AircraftResponse createAircraft(AircraftRequest request, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("Airline does not exists for the ownerID: " + ownerId)
        );
        Aircraft aircraft = AircraftMapper.toEntity(request, airline);
        if (aircraftRepository.existsByCode(aircraft.getCode())) {
            throw new Exception("Aircraft already exists with this code: " + aircraft.getCode());
        }
        if (aircraft.getSeatingCapacity() < aircraft.getTotalSeats()) {
            throw new Exception("seating capacity can't exceed to total seats ");
        }
        aircraft.setAirline(airline);
        aircraft.setCreatedAt(Instant.now());
        aircraft.setUpdatedAt(Instant.now());
        Aircraft savedAircraft = aircraftRepository.save(aircraft);
        return AircraftMapper.toResponse(savedAircraft);
    }

    @Override
    public AircraftResponse getAircraftById(Long id) throws Exception {
        Aircraft aircraft = aircraftRepository.findById(id).orElseThrow(
                () -> new Exception("Aircraft not found with id: " + id)
        );
        return AircraftMapper.toResponse(aircraft);
    }

    @Override
    public AircraftResponse updateAircraft(Long id, AircraftRequest request, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("Aircraft not found with owner id: " + ownerId)
        );

        Aircraft aircraft = aircraftRepository.findByIdAndAirlineId(id, airline.getId());
        if (aircraft == null) {
            throw new Exception("Aircraft does not exist with id ");
        }

        if (request.getCode() != null
                && !request.getCode().equals(aircraft.getCode())
                && aircraftRepository.existsByCode(request.getCode())) {
            throw new Exception("Code already exists with another aircraft");
        }
        AircraftMapper.updateEntity(aircraft, request);
        return AircraftMapper.toResponse(aircraftRepository.save(aircraft));
    }

    @Override
    public void deleteAircraft(Long id, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("Aircraft not found with owner id: " + ownerId)
        );

        Aircraft aircraft = aircraftRepository.findByIdAndAirlineId(id, airline.getId());
        if (aircraft == null) {
            throw new Exception("Aircraft does not exist with id ");
        }

        aircraftRepository.delete(aircraft);
    }

    @Override
    public List<AircraftResponse> listAlAircraftByOwnerId(Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("This owner does not have any airline")
        );
        return aircraftRepository.findByAirlineId(airline.getId())
                .stream()
                .map(AircraftMapper::toResponse).toList();
    }
}
