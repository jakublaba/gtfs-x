package com.krabelard.model.optional;

import com.krabelard.model.enums.PathwayMode;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Optional
@Value
@Builder
public class Pathway {
    long id;                            // Required
    long fromStopId;                    // Required
    long toStopId;                      // Required
    PathwayMode mode;                   // Required
    boolean isBidirectional;            // Required, note: this is denoted by 0/1 in gtfs feed
    Double length;                      // Optional
    Integer traversalTime;              // Optional
    Integer stairCount;                 // Optional
    Float maxSlope;                     // Optional
    Float minWidth;                     // Optional
    String signpostedAs;                // Optional
    String reverseSignpostedAs;         // Optional

    public Optional<Double> getLength() {
        return Optional.ofNullable(length);
    }

    public Optional<Integer> getTraversalTime() {
        return Optional.ofNullable(traversalTime);
    }

    public Optional<Integer> getStairCount() {
        return Optional.ofNullable(stairCount);
    }

    public Optional<Float> getMaxSlope() {
        return Optional.ofNullable(maxSlope);
    }

    public Optional<Float> getMinWidth() {
        return Optional.ofNullable(minWidth);
    }

    public Optional<String> getSignpostedAs() {
        return Optional.ofNullable(signpostedAs);
    }

    public Optional<String> getReverseSignpostedAs() {
        return Optional.ofNullable(reverseSignpostedAs);
    }
}
