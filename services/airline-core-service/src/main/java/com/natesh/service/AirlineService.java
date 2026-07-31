package com.natesh.service;

import com.natesh.enums.AirlineStatus;
import com.natesh.payload.request.AirlineRequest;
import com.natesh.payload.response.AirlineDropDownItem;
import com.natesh.payload.response.AirlineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirlineService {
    AirlineResponse createAirline(AirlineRequest request, Long ownerId);

    AirlineResponse getAirlineByOwner(Long ownerId) throws Exception;

    AirlineResponse getAirlineById(Long id) throws Exception;

//    AirlineResponse updateAirlineById(AirlineRequest request, Long ownerId) throws Exception;

    Page<AirlineResponse> getAllAirlines(Pageable pageable);

    void deleteAirline(Long id, Long ownerId) throws Exception;

    AirlineResponse udpateAirline(AirlineRequest request, Long ownerId) throws Exception;

    AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) throws Exception;

    List<AirlineDropDownItem> getAirlineDropdown();

}
