package com.krabelard.model.optional;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Optional
@Value
@Builder
public class Attribution {
    Long id;                    // Optional
    Long agencyId;              // Optional
    Long routeId;               // Optional
    Long tripId;                // Optional
    String organizationName;    // Required
    boolean isProducer;         // Optional in gtfs feed but defaults to false if not present
    boolean isOperator;         // Optional in gtfs feed but defaults to false if not present
    boolean isAuthority;        // Optional in gtfs feed but defaults to false if not present
    // note: 3 above values are denoted in gtfs feeds as 0/1
    String url;                 // Optional
    String email;               // Optional
    String phone;               // Optional

    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

    public Optional<Long> getAgencyId() {
        return Optional.ofNullable(agencyId);
    }

    public Optional<Long> getRouteId() {
        return Optional.ofNullable(routeId);
    }

    public Optional<Long> getTripId() {
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
