package com.natesh.controller;

import com.natesh.mapper.AircraftMapper;
import com.natesh.payload.request.AircraftRequest;
import com.natesh.payload.response.AircraftResponse;
import com.natesh.repository.AircraftRepository;
import com.natesh.service.AircraftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/aircraft")
public class AircraftController {

    @Autowired
    AircraftService aircraftService;

    @PostMapping("/create")
    public ResponseEntity<?> createAircraft(
            @Valid @RequestBody AircraftRequest request,
            @RequestHeader("X-User-Id") Long userId) throws Exception {
        AircraftResponse aircraft = aircraftService.createAircraft(request, userId);
        return new ResponseEntity<>(aircraft, HttpStatus.CREATED);
    }

    @GetMapping("get-by/{id}")
    public ResponseEntity<?> getAircraftById(
            @PathVariable("id") Long id) throws Exception {
        AircraftResponse aircraft = aircraftService.getAircraftById(id);
        return new ResponseEntity<>(aircraft, HttpStatus.OK);
    }


    @GetMapping("get-all/{userId}")
    public ResponseEntity<?> getAllAircrafts(
            @RequestHeader("X-User-Id") Long userId) throws Exception {
        List<AircraftResponse> aircraftResponses = aircraftService.listAlAircraftByOwnerId(userId);
        return new ResponseEntity<>(aircraftResponses, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(
            @PathVariable("id") Long id,
            @RequestBody AircraftRequest aircraftRequest,
            @RequestHeader("X-User-Id") Long userId) throws Exception {
        return new ResponseEntity<>(aircraftService.updateAircraft(id, aircraftRequest, userId), HttpStatus.OK );

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(
            @PathVariable("id") Long id,
            @RequestHeader("X-User-Id") Long userId) throws Exception {
        aircraftService.deleteAircraft(id,userId);
        return new ResponseEntity<>(HttpStatus.OK);

    }



}
