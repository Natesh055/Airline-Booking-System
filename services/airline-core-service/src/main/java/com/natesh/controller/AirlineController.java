package com.natesh.controller;

import com.natesh.enums.AirlineStatus;
import com.natesh.mapper.AirlineMapper;
import com.natesh.model.Airline;
import com.natesh.payload.request.AirlineRequest;
import com.natesh.payload.response.AirlineDropDownItem;
import com.natesh.payload.response.AirlineResponse;
import com.natesh.payload.response.ApiResponse;
import com.natesh.service.AirlineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/airline")
public class AirlineController {
    @Autowired
    AirlineService airlineService;

    @PostMapping("/create")
    public ResponseEntity<?> createAirline(@Valid @RequestBody AirlineRequest request,
                                           @RequestHeader("X-User-Id") Long userId) {
        AirlineResponse airline = airlineService.createAirline(request, userId);
        return new ResponseEntity<>(airline, HttpStatus.CREATED);
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAirlineByOwner(@RequestHeader("X-User-Id") Long userId) throws Exception {
        AirlineResponse airline = airlineService.getAirlineByOwner(userId);
        return new ResponseEntity<>(airline, HttpStatus.CREATED);
    }

    @GetMapping("/get-by/{id}")
    public ResponseEntity<?> getAirlineById(@PathVariable("id") Long id) throws Exception {
        AirlineResponse airline = airlineService.getAirlineById(id);
        return new ResponseEntity<>(airline, HttpStatus.CREATED);
    }

//    @GetMapping("/get-all")
//    public ResponseEntity<?> getAllAirlines(Pageable pageable) {
//        airlineService.getAllAirlines(pageable);
//    }


    @GetMapping("/get-by/dropdown")
    public ResponseEntity<?> getAirlinesForDropdown(Pageable pageable) {
        List<AirlineDropDownItem> airlineDropdown = airlineService.getAirlineDropdown();
        return new ResponseEntity<>(airlineDropdown, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAirline(@RequestBody @Valid AirlineRequest request,
                                           @RequestHeader("X-User-Id") Long userId) throws Exception {
        AirlineResponse response = airlineService.udpateAirline(request, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMapping(@PathVariable("id") Long id,
                                           @RequestHeader("X-User-Id") Long userId) throws Exception {
        airlineService.deleteAirline(id, userId);
        ApiResponse response = new ApiResponse("Airline deleted successfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<?> approveAirline(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(airlineService.changeStatusByAdmin(id, AirlineStatus.ACTIVE), HttpStatus.OK);
    }

    @PostMapping("/{id}/ban")
    public ResponseEntity<?> banAirline(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(airlineService.changeStatusByAdmin(id, AirlineStatus.BANNED), HttpStatus.OK);
    }

    @PostMapping("/{id}/suspend")
    public ResponseEntity<?> suspendAirline(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(airlineService.changeStatusByAdmin(id, AirlineStatus.INACTIVE), HttpStatus.OK);
    }
}
