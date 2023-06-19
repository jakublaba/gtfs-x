package com.krabelard.model.optional.gtfs_fares_v1;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Optional
// Associated with GTFS-Fares V1
@Value
@Builder
public class FareRule {
    long fareId;        // Required
    Long routeId;       // Optional
    Long originId;      // Optional
    Long destinationId; // Optional
    Long containsId;    // Optional

    public Optional<Long> getRouteId() {
        return Optional.ofNullable(routeId);
    }

    public Optional<Long> getOriginId() {
        return Optional.ofNullable(originId);
    }

    public Optional<Long> getDestinationId() {
        return Optional.ofNullable(destinationId);
    }

    public Optional<Long> getContainsId() {
        return Optional.ofNullable(containsId);
    }
}
