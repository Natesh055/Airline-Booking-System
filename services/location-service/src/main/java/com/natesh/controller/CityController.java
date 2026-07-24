package com.natesh.controller;


import com.natesh.payload.request.CityRequest;
import com.natesh.payload.response.CityResponse;
import com.natesh.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping("/create-city")
    public ResponseEntity<?> createCity(@Valid @RequestBody CityRequest cityRequest) throws Exception {
        CityResponse response = cityService.createCity(cityRequest);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}

