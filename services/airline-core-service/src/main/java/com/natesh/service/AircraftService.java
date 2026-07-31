package com.natesh.service;

import com.natesh.payload.request.AircraftRequest;
import com.natesh.payload.response.AircraftResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AircraftService {
    AircraftResponse createAircraft(AircraftRequest request, Long ownerId) throws Exception;

    AircraftResponse getAircraftById(Long id) throws Exception;

    AircraftResponse updateAircraft(Long id,AircraftRequest request, Long ownerId) throws Exception;

    void deleteAircraft(Long id, Long ownerId) throws Exception;

    List<AircraftResponse> listAlAircraftByOwnerId(Long ownerId) throws Exception;
}
