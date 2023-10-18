package com.krabelard.model.required;

import com.krabelard.model.enums.DropOffType;
import com.krabelard.model.enums.LocationType;
import com.krabelard.model.enums.PickupType;
import com.krabelard.model.enums.TimePoint;
import com.krabelard.model.optional.Shape;
import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;
import java.util.Optional;

/**
 * Maps an entry from required <code>stops_times.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#stop_timestxt">the gtfs reference</a>
 */
@Value
@Builder
public class StopTime {
    /**
     * <code>trip_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a trip.
     */
    String tripId;
    /**
     * <code>arrival_time</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Arrival time at the stop (defined by {@link StopTime#stopId}) for a specific trip (defined by {@link StopTime#tripId}).
     * If there are not separate times for arrival and departure at a stop, this field and {@link StopTime#departureTime}
     * should be the same.
     * For times occurring after midnight on the service day, enter the time as a value greater than 24:00:00 in HH:MM:SS
     * local time for the day on which the trip schedule begins.
     * If exact arrival and departure times (<code>{@link StopTime#timePoint}={@link TimePoint#EXACT}</code>) are not available,
     * estimated or interpolated arrival and departure times (<code>{@link StopTime#timePoint}={@link TimePoint#APPROXIMATE}</code>) should be provided.
     * <br>
     * Required for the first and last stop in a trip (defined by {@link StopTime#stopSequence}).
     * <br>
     * Required for <code>{@link StopTime#timePoint}={@link TimePoint#EXACT}</code>
     */
    LocalTime arrivalTime;
    /**
     * <code>departure_time</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Departure time from the stop (defined by {@link StopTime#stopId}) for a specific trip (defined by {@link StopTime#tripId}).
     * If there are not separate times for arrival and departure at a stop, this field and {@link StopTime#departureTime}
     * should be the same.
     * For times occurring after midnight on the service day, enter the time as a value greater than 24:00:00 in HH:MM:SS
     * local time for the day on which the trip schedule begins.
     * If exact arrival and departure times (<code>{@link StopTime#timePoint}={@link TimePoint#EXACT}</code>) are not available,
     * estimated or interpolated arrival and departure times (<code>{@link StopTime#timePoint}={@link TimePoint#APPROXIMATE}</code>) should be provided.
     * <br>
     * Required for <code>{@link StopTime#timePoint}={@link TimePoint#EXACT}</code>
     */
    LocalTime departureTime;
    /**
     * <code>stop_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies the serviced stop. All stops serviced during a trip must have a record in {@link StopTime} entities.
     * Referenced locations must be stops/platforms (<code>{@link Stop#locationType}={@link LocationType#STOP_OR_PLATFORM}</code>).
     * A stop may be serviced multiple times in the same trip, and multiple trips and routes may service the same stop.
     */
    String stopId;
    /**
     * <code>stop_sequence</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Order of stops for a particular trip. The values must increase along the trip but do not need to be consecutive.
     */
    int stopSequence;
    /**
     * <code>stop_headsign</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Text that appears on the signage identifying the trip's destination to riders. This field overrides the default
     * {@link Trip#headSign} when the headsign changes between stops. If the headsign is displayed for an entire trip,
     * {@link Trip#headSign} should be used instead.
     */
    String stopHeadSign;
    /**
     * <code>pickup_type</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Indicates pickup method.
     */
    PickupType pickupType;
    /**
     * <code>drop_off_type</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Indicates drop off method.
     */
    DropOffType dropOffType;
    /**
     * <code>continuous_pickup</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Indicates that the rider can board the transit vehicle at any point along the vehicle's travel path as described
     * by {@link Shape} entities, from this {@link StopTime} to the next {@link StopTime} in the trip's {@link StopTime#stopSequence}.
     * <br>
     * If this field is populated, it overrides any continuous pickup behavior defined in {@link Route} entities.
     * If this field is left empty, this entity inherits any continuous pickup behavior defined in {@link Route} entities.
     */
    PickupType continuousPickup;
    /**
     * <code>continuous_drop_off</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Indicates that the rider can alight from the transit vehicle at any point along the vehicle's travel path as described
     * by {@link Shape} entities, from this {@link StopTime} to the next {@link StopTime} in the trip's {@link StopTime#stopSequence}.
     * <br>
     * If this field is populated, it overrides any continuous drop-off behavior defined in {@link Route} entities.
     * If this field is left empty, this entity inherits any continuous drop-off behavior defined in {@link Route} entities.
     */
    DropOffType continuousDropOff;
    /**
     * <code>shape_dist_traveled</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Actual distance traveled along the associated shape, from the first stop to the stop specified in this entity.
     * This field specified how much of the shape to draw between any two stops during a trip. Must be in the same units
     * used in {@link Shape} entities. Values used for this field must increase along with {@link StopTime#stopSequence};
     * they must not be used to show reverse travel along a route.
     */
    Double shapeDistanceTraveled;
    /**
     * <code>timepoint</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Indicates if arrival and departure times for a stop are strictly adhered to by the vehicle or if they are instead
     * approximate and/or interpolated times. This field allows a GTFS producer to provide interpolated stop-times, while
     * indicating that the times are approximate.
     */
    TimePoint timePoint;

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
