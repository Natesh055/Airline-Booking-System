package com.natesh.model;

import com.natesh.embeddable.Support;
import com.natesh.enums.AirlineStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String iataCode;

    @Column(nullable = false, unique = true)
    private String icaoCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long ownerId;

    private String alias;

    private String logoUrl;

    private String website;

    @Enumerated(EnumType.STRING)
    private AirlineStatus status = AirlineStatus.ACTIVE;

    private String alliance;

    private Long hqCityId;

    private Long updatedById;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Instant updatedAt;

    @Embedded
    private Support support;

}
