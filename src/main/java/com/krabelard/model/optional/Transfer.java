package com.krabelard.model.optional;

import com.krabelard.model.enums.TransferType;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Optional
@Value
@Builder
public class Transfer {
    Long fromStopId;            // Conditionally required
    Long toStopId;              // Conditionally required
    Long fromRouteId;           // Optional
    Long toRouteId;             // Optional
    Long fromTripId;            // Conditionally required
    Long toTripId;              // Conditionally required
    TransferType transferType;  // Required
    Integer minTransferTime;    // Optional

    public Optional<Long> getFromStopId() {
        return Optional.ofNullable(fromStopId);
    }

    public Optional<Long> getToStopId() {
        return Optional.ofNullable(toStopId);
    }

    public Optional<Long> getFromRouteId() {
        return Optional.ofNullable(fromRouteId);
    }

    public Optional<Long> getToRouteId() {
        return Optional.ofNullable(toRouteId);
    }

    public Optional<Long> getFromTripId() {
        return Optional.ofNullable(fromTripId);
    }

    public Optional<Long> getToTripId() {
        return Optional.ofNullable(toTripId);
    }

    public Optional<Integer> getMinTransferTime() {
        return Optional.ofNullable(minTransferTime);
    }
}
