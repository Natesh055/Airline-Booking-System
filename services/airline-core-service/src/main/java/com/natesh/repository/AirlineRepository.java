package com.natesh.repository;

import com.natesh.enums.AirlineStatus;
import com.natesh.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Optional<Airline> findByOwnerId(Long ownerId);
    List<Airline> findByStatus(AirlineStatus status);

}
