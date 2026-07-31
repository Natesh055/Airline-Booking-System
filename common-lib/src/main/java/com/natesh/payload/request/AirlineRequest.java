package com.natesh.payload.request;

import com.natesh.embeddable.Support;
import com.natesh.enums.AirlineStatus;
import com.natesh.payload.DTO.UserDTO;
import com.natesh.payload.response.CityResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirlineRequest {
    @NotBlank(message = "IATA Code is mandatory")
    @Size(min = 2, max = 2, message = "IATA code must be of length 2")
    private String iataCode;

    @NotBlank(message = "ICAO Code is mandatory")
    @Size(min = 3, max = 3, message = "ICAO code must be of length 2")
    private String icaoCode;

    @NotBlank(message = "Airline name is mandatory")
    private String name;

    private String alias;

    private String logoUrl;
    private String website;

    private AirlineStatus status;
    private String alliance;

    private Long hqCityId;
    private Long ownerId;

    private String supportEmail;
    private String supportHours;
    private String supportPhone;

}
