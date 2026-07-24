package com.natesh.service;

import com.natesh.payload.request.CityRequest;
import com.natesh.payload.response.CityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CityService {

    CityResponse createCity (CityRequest cityRequest) throws Exception;
    CityResponse getCityById (Long id) throws Exception;
    CityResponse updateCity (Long id,CityRequest req) throws Exception;
    void deleteCity (Long id) throws Exception;

    Page<CityResponse> getAllCities(Pageable pageable);
    Page<CityResponse> searchCities(String keyword, Pageable pageable);
    Page<CityResponse> getCityByCountryCode(String countryCode, Pageable pageable);
    boolean cityExists(String cityCode);
    boolean validateCityCode(String cityCode);

}
