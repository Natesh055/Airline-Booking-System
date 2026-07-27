package com.natesh.payload.request;

import com.natesh.embeddable.Address;
import com.natesh.embeddable.GeoCode;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportRequest {
    @NotBlank(message = "IATA Code is mandatory")
    @Size(min = 3, max = 3, message = "IATA code must be of length 3")
    private String iataCode;

    @NotBlank(message = "Airport name is mandatory")
    private String name;

    @Valid
    private Address address;

    @NotNull(message = "City Id is mandatory")
    private long cityId;

    private ZoneId timeZone;

    @Valid
    @Embedded
    private GeoCode geoCode;

}
