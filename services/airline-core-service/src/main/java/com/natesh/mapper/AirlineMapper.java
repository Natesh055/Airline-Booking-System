package com.natesh.mapper;

import com.natesh.embeddable.Support;
import com.natesh.model.Airline;
import com.natesh.payload.request.AirlineRequest;
import com.natesh.payload.response.AirlineResponse;
import org.springframework.stereotype.Component;

@Component
public class AirlineMapper {

    public static Airline toEntity(AirlineRequest request, Long ownerId) {
        if (request == null) {
            return null;
        }
        Airline airline = Airline.builder()
                .iataCode(request.getIataCode())
                .icaoCode(request.getIcaoCode())
                .alias(request.getAlias())
                .logoUrl(request.getLogoUrl())
                .website(request.getWebsite())
                .status(request.getStatus())
                .alliance(request.getAlliance())
                .hqCityId(request.getHqCityId())
                .ownerId(request.getOwnerId())
                .build();

        if (request.getSupportEmail() != null
                || request.getSupportPhone() != null
                || request.getSupportHours() != null) {
            airline.setSupport(
                    Support.builder()
                            .email(request.getSupportEmail())
                            .hours(request.getSupportHours())
                            .phone(request.getSupportPhone())
                            .build());
        }
        return airline;
    }

    public static AirlineResponse toResponse(Airline airline) {
        if (airline == null) {
            return null;
        }

        return AirlineResponse.builder()
                .id(airline.getId())
                .iataCode(airline.getIataCode())
                .icaoCode(airline.getIcaoCode())
                .name(airline.getName())
                .alias(airline.getAlias())
                .logoUrl(airline.getLogoUrl())
                .website(airline.getWebsite())
                .status(airline.getStatus())
                .alliance(airline.getAlliance())
                .support(airline.getSupport())
                .createdAt(airline.getCreatedAt())
                .udpatedAt(airline.getUpdatedAt())
                .ownerId(airline.getOwnerId())
                .updatedById(airline.getUpdatedById())
                .build();
    }

    public static void updateEntity(Airline airline, AirlineRequest request) {
        if (airline == null || request == null) {
            return;
        }

        if (request.getIataCode() != null) {
            airline.setIataCode(request.getIataCode());
        }

        if (request.getIcaoCode() != null) {
            airline.setIcaoCode(request.getIcaoCode());
        }

        if (request.getName() != null) {
            airline.setName(request.getName());
        }

        if (request.getAlias() != null) {
            airline.setAlias(request.getAlias());
        }

        if (request.getLogoUrl() != null) {
            airline.setLogoUrl(request.getLogoUrl());
        }

        if (request.getWebsite() != null) {
            airline.setWebsite(request.getWebsite());
        }

        if (request.getStatus() != null) {
            airline.setStatus(request.getStatus());
        }

        if (request.getAlliance() != null) {
            airline.setAlliance(request.getAlliance());
        }

        if (request.getHqCityId() != null) {
            airline.setHqCityId(request.getHqCityId());
        }

        if (request.getOwnerId() != null) {
            airline.setOwnerId(request.getOwnerId());
        }

        if (request.getSupportEmail() != null
                || request.getSupportPhone() != null
                || request.getSupportHours() != null) {

            Support support = airline.getSupport();
            if (support == null) {
                support = new Support();
            }

            if (request.getSupportEmail() != null) {
                support.setEmail(request.getSupportEmail());
            }

            if (request.getSupportPhone() != null) {
                support.setPhone(request.getSupportPhone());
            }

            if (request.getSupportHours() != null) {
                support.setHours(request.getSupportHours());
            }

            airline.setSupport(support);
        }
    }
}
