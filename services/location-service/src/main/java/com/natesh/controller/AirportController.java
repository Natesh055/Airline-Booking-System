package com.natesh.controller;

import com.natesh.mapper.AirportMapper;
import com.natesh.model.Airport;
import com.natesh.payload.request.AirportRequest;
import com.natesh.payload.response.AirportResponse;
import com.natesh.service.AirportService;
import com.natesh.service.Impl.AirportServiceImpl;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airport")
public class AirportController {
    @Autowired
    private final AirportService airportService;

    @PostMapping("/create")
    public ResponseEntity<?> createAirport(@RequestBody @Valid AirportRequest request) throws Exception {
        AirportResponse airport = airportService.createAirport(request);
        return new ResponseEntity<>(airport, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/id/{id}")
    public ResponseEntity<?> getAirportById(@PathVariable("id") Long airportId) throws Exception {
        return new ResponseEntity(airportService.getAirportById(airportId), HttpStatus.OK);
    }

    @GetMapping("/get-by/city/{cityId}")
    public ResponseEntity<?> getAirportsByCity(@PathVariable("cityId") Long cityId) {
        List<AirportResponse> airportsByCityId = airportService.getAirportsByCityId(cityId);
        return new ResponseEntity<>(airportsByCityId, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAirport(@PathVariable("id") Long id, @Valid AirportRequest request) throws Exception {
        AirportResponse airportResponse = airportService.updateAirport(id, request);
        return new ResponseEntity<>(airportResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable("id")  Long id) throws Exception {
        AirportResponse airportById = airportService.getAirportById(id);
        if (airportById != null) {
            airportService.deleteAirport(id);
        } else {
            throw new Exception("Airport with id does not exist, hence cannot delete");
        }
    }
}
