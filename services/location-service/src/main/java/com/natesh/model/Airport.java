package com.natesh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.natesh.embeddable.Address;
import com.natesh.embeddable.GeoCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false, length = 3)
    private String iataCode;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    @Embedded
    private GeoCode geoCode;

    @Column(name = "time_zone_id", length = 50)
    private ZoneId timeZone;

    @ManyToOne
    @JsonIgnore
    private City city;

    @JsonIgnore
    @Transient
    public String getDetailedName() {
        if (this.city != null && this.city.getCountryCode() != null) {
            return name.toUpperCase() + "/" + city.getCityCode();
        }
        return name.toUpperCase();
    }
}

