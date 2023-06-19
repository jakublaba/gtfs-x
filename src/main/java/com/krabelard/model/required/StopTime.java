package com.krabelard.model.required;

import com.krabelard.model.enums.DropOffType;
import com.krabelard.model.enums.PickupType;
import com.krabelard.model.enums.TimePoint;
import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;
import java.util.Optional;

// Required
@Value
@Builder
public class StopTime {
    long tripId;                    // Required
    LocalTime arrivalTime;          // Conditionally required
    LocalTime departureTime;        // Conditionally required
    long stopId;                    // Required
    int stopSequence;               // Required
    String stopHeadSign;            // Optional
    PickupType pickupType;          // Optional
    DropOffType dropOffType;        // Optional
    PickupType continuousPickup;    // Optional
    DropOffType continuousDropOff;  // Optional
    Double shapeDistanceTraveled;   // Optional
    TimePoint timePoint;            // Optional

    public Optional<LocalTime> getArrivalTime() {
        return Optional.ofNullable(arrivalTime);
    }

    public Optional<LocalTime> getDepartureTime() {
        return Optional.ofNullable(departureTime);
    }

    public Optional<String> getStopHeadSign() {
        return Optional.ofNullable(stopHeadSign);
    }

    public Optional<PickupType> getPickupType() {
        return Optional.ofNullable(pickupType);
    }

    public Optional<DropOffType> getDropOffType() {
        return Optional.ofNullable(dropOffType);
    }

    public Optional<PickupType> getContinuousPickup() {
        return Optional.ofNullable(continuousPickup);
    }

    public Optional<DropOffType> getContinuousDropOff() {
        return Optional.ofNullable(continuousDropOff);
    }

    public Optional<Double> getShapeDistanceTraveled() {
        return Optional.ofNullable(shapeDistanceTraveled);
    }

    public Optional<TimePoint> getTimePoint() {
        return Optional.ofNullable(timePoint);
    }
}
