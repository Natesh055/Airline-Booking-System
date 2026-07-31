package com.natesh.service.impl;

import com.natesh.enums.AirlineStatus;
import com.natesh.mapper.AirlineMapper;
import com.natesh.model.Airline;
import com.natesh.payload.request.AirlineRequest;
import com.natesh.payload.response.AirlineDropDownItem;
import com.natesh.payload.response.AirlineResponse;
import com.natesh.repository.AirlineRepository;
import com.natesh.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {
    @Autowired
    AirlineRepository airlineRepository;


    @Override
    public AirlineResponse createAirline(AirlineRequest request, Long ownerId) {
        Airline airline = AirlineMapper.toEntity(request, ownerId);
        airline.setCreatedAt(Instant.now());
        airline.setUpdatedAt(Instant.now());
        Airline savedAirline = airlineRepository.save(airline);
        return AirlineMapper.toResponse(savedAirline);
    }

    @Override
    public AirlineResponse getAirlineByOwner(Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("Owner not found of the airline."));
        return AirlineMapper.toResponse(airline);
    }

    @Override
    public Page<AirlineResponse> getAllAirlines(Pageable pageable) {
        return airlineRepository.findAll(pageable).map(
                AirlineMapper::toResponse
        );
    }

    @Override
    public AirlineResponse getAirlineById(Long id) throws Exception {
        Airline airline = airlineRepository.findById(id).orElseThrow(
                () -> new Exception("Airline not found with the id: " + id));
        return AirlineMapper.toResponse(airline);
    }


    @Override
    public AirlineResponse udpateAirline(AirlineRequest request, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("Airline not found with the owner id: " + ownerId));

        AirlineMapper.updateEntity(airline, request);
        Airline updatedAirline = airlineRepository.save(airline);
        return AirlineMapper.toResponse(updatedAirline);
    }


    @Override
    public void deleteAirline(Long id, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("Airline not found with the owner id: " + ownerId));
        airlineRepository.delete(airline);
    }

    @Override
    public AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) throws Exception {
        Airline airline = airlineRepository.findById(airlineId).orElseThrow(
                () -> new Exception("Airline not found with the id: " + airlineId));
        airline.setStatus(status);
        Airline updatedeAirline = airlineRepository.save(airline);
        return AirlineMapper.toResponse(updatedeAirline);
    }

    @Override
    public List<AirlineDropDownItem> getAirlineDropdown() {
        return airlineRepository.findByStatus(AirlineStatus.ACTIVE)
                .stream()
                .map(a -> AirlineDropDownItem.builder()
                        .id(a.getId())
                        .name(a.getName())
                        .iataCode(a.getIataCode())
                        .logoUrl(a.getLogoUrl())
                        .build()).toList();
    }
}
