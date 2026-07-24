package com.natesh.payload.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {
    private long id;
    private String name;
    private String cityCode;
    private String countryCode;
    private String countryName;
    private String regionCode;
    private String timeZoneOffset;
}
