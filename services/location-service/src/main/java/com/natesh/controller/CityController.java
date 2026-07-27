package com.natesh.controller;


import com.natesh.payload.request.CityRequest;
import com.natesh.payload.response.CityResponse;
import com.natesh.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping("/create-city")
    public ResponseEntity<?> createCity(@Valid @RequestBody CityRequest cityRequest) throws Exception {
        CityResponse response = cityService.createCity(cityRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCityById(@PathVariable("id") Long id) throws Exception {
        CityResponse cityResponse = cityService.getCityById(id);
        return new ResponseEntity<>(cityResponse, HttpStatus.OK);
    }

    @GetMapping("/get-all-cities")
    public ResponseEntity<?> getAllCities(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(cityService.getAllCities(pageable));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateCityById(@PathVariable("id") Long id, @RequestBody CityRequest request) throws Exception {
        CityResponse response = cityService.updateCity(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCityById(@PathVariable("id") Long id) throws Exception {
        cityService.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search-cities")
    public ResponseEntity<?> searchAllCities(@RequestParam(name = "keyword") String keyword,
                                             @RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(cityService.searchCities(keyword, pageable));
    }

    @GetMapping("/search/countryCode/{countryCode}")
    public ResponseEntity<?> searchCityByCountryCode(@PathVariable String countryCode,
                                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(cityService.getCityByCountryCode(countryCode.toUpperCase(), pageable), HttpStatus.OK);
    }

}

