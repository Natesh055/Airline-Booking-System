package com.natesh.repository;

import com.natesh.model.Airport;
import com.natesh.payload.response.AirportResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByIataCode(String iataCode);

    List<Airport> findByCityId(Long CityId);


}
