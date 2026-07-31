package com.natesh.payload.response;

import com.natesh.embeddable.Support;
import com.natesh.enums.AirlineStatus;
import com.natesh.payload.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirlineDropDownItem {
    private long id;
    private String name;
    private String iataCode;
    private String icaoCode;
    private String logoUrl;
    private String coutry;
}
