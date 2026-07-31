package com.natesh.repository;

import com.natesh.mapper.AircraftMapper;
import com.natesh.model.Aircraft;
import com.natesh.payload.response.AircraftResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AircraftRepository extends JpaRepository<Aircraft,Long> {
    List<Aircraft> findByAirlineId(Long airlineId);
    Boolean existsByCode(String code);
    Aircraft findByIdAndAirlineId(Long id,Long airlineId);
}
