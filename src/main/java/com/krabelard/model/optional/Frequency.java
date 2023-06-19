package com.krabelard.model.optional;

import com.krabelard.model.enums.ServiceType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;
import java.util.Optional;

// Optional
@Value
@Builder
public class Frequency {
    long tripId;            // Required
    LocalTime startTime;    // Required
    LocalTime endTime;      // Required
    int headwaySecs;        // Required
    ServiceType exactTimes; // Optional

    public Optional<ServiceType> getExactTimes() {
        return Optional.ofNullable(exactTimes);
    }
}
