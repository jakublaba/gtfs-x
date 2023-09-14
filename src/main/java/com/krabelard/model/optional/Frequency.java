package com.krabelard.model.optional;

import com.krabelard.model.enums.ServiceType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;
import java.util.Optional;

/**
 * Maps an entry from optional <code>frequencies.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#frequenciestxt">the gtfs reference</a>
 */
@Value
@Builder
public class Frequency {
    /**
     * <code>trip_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a trip to which the specified headway of service applies.
     */
    String tripId;
    /**
     * <code>start_time</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Time at which the first vehicle departs from the first stop of the trip with the specified headway.
     */
    LocalTime startTime;
    /**
     * <code>end_time</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Time at which service changes to a different headway (or ceases) at the first stop in the trip.
     */
    LocalTime endTime;
    /**
     * <code>headway_secs</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Time, in seconds. between departures from the same stop (headway) for the trip, during the time interval specified
     * by {@link Frequency#startTime} and {@link Frequency#endTime}. Multiple headways may be defined for the same trip,
     * but must not overlap. New headways may start aat the exact time the previous headway ends.
     */
    int headwaySecs;
    /**
     * <code>exact_times</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Indicates the type of service for a trip.
     */
    ServiceType exactTimes;

    public Optional<ServiceType> getExactTimes() {
        return Optional.ofNullable(exactTimes);
    }
}
