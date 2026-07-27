package com.natesh.payload.response;

import com.natesh.embeddable.Address;
import com.natesh.embeddable.GeoCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.util.TimeZone;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportResponse {
    private long id;
    private String iataCode;
    private String name;
    private String detailedName;
    private Address address;
    private ZoneId zoneId;
    private CityResponse city;
    private GeoCode geoCode;
    private ZoneId timeZone;
}
