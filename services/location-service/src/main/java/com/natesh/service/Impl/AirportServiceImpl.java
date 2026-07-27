package com.natesh.service.Impl;

import com.natesh.mapper.AirportMapper;
import com.natesh.model.Airport;
import com.natesh.model.City;
import com.natesh.payload.request.AirportRequest;
import com.natesh.payload.response.AirportResponse;
import com.natesh.repository.AirportRepository;
import com.natesh.repository.CityRepository;
import com.natesh.service.AirportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public AirportResponse createAirport(AirportRequest request) throws Exception {
        if (airportRepository.findByIataCode(request.getIataCode()).isPresent()) {
            throw new Exception("Airport with given Iata code already exists");
        }
        City city = cityRepository.findById(request.getCityID()).orElseThrow(
                () -> new Exception("City not found")
        );

        Airport airport = AirportMapper.toEntity(request);
        Airport savedAirport = airportRepository.save(airport);
        return AirportMapper.toResponse(airport);
    }

    @Override
    public AirportResponse getAirportById(Long id) throws Exception {
        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new Exception("Airport does not exist with Id")
        );
        return AirportMapper.toResponse(airport);
    }

    @Override
    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportRequest request) throws Exception {
        Airport existingAirport = airportRepository.findById(id).orElseThrow(
                () -> new Exception("Airport with given id does not exists with id: " + id)
        );
        if (request.getIataCode() != null &&
                !existingAirport.getIataCode().equals(request.getIataCode()) &&
                airportRepository.findByIataCode(request.getIataCode()).isPresent()) {
            throw new Exception("Airport with IATA code already exists.");
        }
        AirportMapper.updateEntity(request, existingAirport);
        Airport updatedAirport = airportRepository.save(existingAirport);
        return AirportMapper.toResponse(updatedAirport);
    }

    @Override
    public void deleteAirport(Long id) throws Exception {
        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new Exception("Airport with given id does not exists with id: " + id)
        );
        airportRepository.delete(airport);
    }

    @Override
    public List<AirportResponse> getAirportsByCityId(Long cityId) {
        return airportRepository.findByCityId(cityId)
                .stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }
}
