package com.natesh.payload.response;

import com.natesh.embeddable.Support;
import com.natesh.enums.AirlineStatus;
import com.natesh.payload.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirlineResponse {
    private long id;
    private String iataCode;
    private String icaoCode;

    private String name;
    private String alias;

    private String logoUrl;
    private String website;

    private AirlineStatus status;
    private String alliance;

    private Long ownerId;
    private UserDTO owner;

    private Long updatedById;
    private CityResponse hqCity;
    private Support support;

    private Instant createdAt;
    private Instant udpatedAt;

}
