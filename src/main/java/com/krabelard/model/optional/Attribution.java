package com.krabelard.model.optional;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// TODO Add docs to Attribution entity
// I forgor xd
// Optional
@Value
@Builder
public class Attribution {
    String id;                    // Optional
    String agencyId;              // Optional
    String routeId;               // Optional
    String tripId;                // Optional
    String organizationName;    // Required
    boolean isProducer;         // Optional in gtfs feed but defaults to false if not present
    boolean isOperator;         // Optional in gtfs feed but defaults to false if not present
    boolean isAuthority;        // Optional in gtfs feed but defaults to false if not present
    // note: 3 above values are denoted in gtfs feeds as 0/1
    String url;                 // Optional
    String email;               // Optional
    String phone;               // Optional

    public Optional<String> getId() {
        return Optional.ofNullable(id);
    }

    public Optional<String> getAgencyId() {
        return Optional.ofNullable(agencyId);
    }

    public Optional<String> getRouteId() {
        return Optional.ofNullable(routeId);
    }

    public Optional<String> getTripId() {
        return Optional.ofNullable(tripId);
    }

    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public Optional<String> getPhone() {
        return Optional.ofNullable(phone);
    }
}
