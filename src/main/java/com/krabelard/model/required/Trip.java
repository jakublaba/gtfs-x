package com.krabelard.model.required;

import com.krabelard.model.enums.BikesAllowed;
import com.krabelard.model.enums.Direction;
import com.krabelard.model.enums.WheelchairAccessibility;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Required
@Value
@Builder
public class Trip {
    long routeId;                                   // Required
    long serviceId;                                 // Required
    long id;                                        // Required
    String headSign;                                // Optional
    String shortName;                               // Optional
    Direction direction;                            // Optional
    Long blockId;                                   // Optional
    Long shapeId;                                   // Optional
    WheelchairAccessibility wheelchairAccessible;   // Optional
    BikesAllowed bikesAllowed;                      // Optional

    public Optional<String> getHeadSign() {
        return Optional.ofNullable(headSign);
    }

    public Optional<String> getShortName() {
        return Optional.ofNullable(shortName);
    }

    public Optional<Direction> getDirection() {
        return Optional.ofNullable(direction);
    }

    public Optional<Long> getBlockId() {
        return Optional.ofNullable(blockId);
    }

    public Optional<Long> getShapeId() {
        return Optional.ofNullable(shapeId);
    }

    public Optional<WheelchairAccessibility> getWheelchairAccessible() {
        return Optional.ofNullable(wheelchairAccessible);
    }

    public Optional<BikesAllowed> getBikesAllowed() {
        return Optional.ofNullable(bikesAllowed);
    }
}
