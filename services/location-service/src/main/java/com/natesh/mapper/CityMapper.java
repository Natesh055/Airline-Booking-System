package com.natesh.mapper;

import com.natesh.model.City;
import com.natesh.payload.request.CityRequest;
import com.natesh.payload.response.CityResponse;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    public static City toEntity(CityRequest req){
        if(req.equals(null))
            return null;
        return City.builder()
                .name(req.getName())
                .cityCode(req.getCityCode())
                .countryCode(req.getCountryCode())
                .countryName(req.getCountryName())
                .regionCode(req.getRegionCode())
                .timeZoneId(req.getTimeZoneOffset())
                .build();
    }
    public static CityResponse toResponse (City city){
         if(city.equals(null))
             return null;

         return CityResponse.builder()
                 .id(city.getId())
                 .name(city.getName())
                 .cityCode(city.getCityCode())
                 .countryCode(city.getCountryCode())
                 .countryName(city.getCountryName())
                 .regionCode(city.getRegionCode())
                 .build();
    }

    public static City updateEntity(City city, CityRequest req){
        if(req.getName()!=null)
            city.setName(req.getName().trim());

        if(req.getCityCode()!=null)
            city.setCityCode(req.getCityCode().toUpperCase().trim());

        if(req.getCountryCode()!=null)
            city.setCountryCode(req.getCountryCode().toUpperCase().trim());

        if(req.getRegionCode()!=null)
            city.setRegionCode(req.getRegionCode().toUpperCase().trim());
        return city;
    }



}
