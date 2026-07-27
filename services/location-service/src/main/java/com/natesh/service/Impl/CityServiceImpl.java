package com.natesh.service.Impl;


import com.natesh.mapper.CityMapper;
import com.natesh.model.City;
import com.natesh.payload.request.CityRequest;
import com.natesh.payload.response.CityResponse;
import com.natesh.repository.CityRepository;
import com.natesh.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CityMapper cityMapper;

    @Override
    public CityResponse createCity(CityRequest cityRequest) throws Exception {
        if (cityRepository.existsByCityCode(cityRequest.getCityCode())) {
            throw new Exception("City with given code already exists");
        }
        City city = CityMapper.toEntity(cityRequest);
        City result = cityRepository.save(city);
        return cityMapper.toResponse(result);

    }

    @Override
    public CityResponse getCityById(Long id) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new Exception("City does not exist with given id")
        );
        return CityMapper.toResponse(city);
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest req) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new Exception("City does not exist with given id")
        );
//        if(cityRepository.existsByCityCode(req.getCityCode()) == false){
//            throw new
//        }
        City updatedCity = cityRepository.save(cityMapper.updateEntity(city, req));
        return CityMapper.toResponse(updatedCity);
    }

    @Override
    public void deleteCity(Long id) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new Exception("City does not exist with given id")
        );
        cityRepository.delete(city);
    }


    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable)
                .map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return cityRepository.searchByKeyword(keyword, pageable)
                .map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> getCityByCountryCode(String countryCode, Pageable pageable) {
        return cityRepository.findByCountryCodeIgnoreCase(countryCode, pageable)
                .map(CityMapper::toResponse);
    }

    @Override
    public boolean cityExists(String cityCode) {
        return cityRepository.existsByCityCode(cityCode);
    }

    @Override
    public boolean validateCityCode(String cityCode) {
        return false;
    }

}
