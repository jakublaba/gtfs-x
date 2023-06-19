package com.krabelard.model.optional;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Optional
@Value
@Builder
public class Shape {
    long id;                // Required
    double ptLatitude;      // Required
    double ptLongitude;     // Required
    int ptSequence;         // Required
    Double distTraveled;    // Optional

    public Optional<Double> getDistTraveled() {
        return Optional.ofNullable(distTraveled);
    }
}
