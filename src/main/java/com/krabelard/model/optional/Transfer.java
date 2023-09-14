package com.krabelard.model.optional;

import com.krabelard.model.enums.TransferType;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from optional <code>transfers.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#transferstxt">the gtfs reference</a>
 */
@Value
@Builder
public class Transfer {
    /**
     * <code>from_stop_id</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Identifies a stop or station where a connection between routes begins. If this field refers to a station, the transfer
     * rule applies to all its child stops. Referring to a station if forbidden for {@link TransferType#InSeat} and {@link TransferType#ReBoard}.
     */
    String fromStopId;
    /**
     * <code>to_stop_id</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Identifies a stop or station where a connection between routes ends. If this field refers to a station, the transfer
     * rule applies to all its child stops. Referring to a station is forbidden for {@link TransferType#InSeat} and {@link TransferType#ReBoard}.
     */
    String toStopId;
    /**
     * <code>from_route_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies a route where a connection begins.
     * <p>
     * If this field is defined, the transfer will apply to the arriving trip on the route for the given {@link Transfer#fromStopId}.
     * <p>
     * If both {@link Transfer#fromTripId} and {@link Transfer#fromRouteId} are defined, the {@link com.krabelard.model.required.Trip#id}
     * must belong the the {@link com.krabelard.model.required.Route#id}, and {@link Transfer#fromTripId} will take precedence.
     */
    String fromRouteId;
    /**
     * <code>to_route_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies a route where connection ends.
     * <p>
     * If this field is defined, the transfer will apply to the departing trip on the route for the given {@link Transfer#toStopId}.
     * <p>
     * If both {@link Transfer#fromTripId} and this field are defined, the {@link com.krabelard.model.required.Trip#id}
     * must belong to the {@link com.krabelard.model.required.Route#id}, and {@link Transfer#fromTripId} will take precedence.
     */
    String toRouteId;
    /**
     * <code>from_trip_id</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Identifies a trip where a connection between routes begins.
     * <p>
     * If this field is defined, the transfer will apply to the arriving trip for the given {@link Transfer#fromStopId}.
     * <p>
     * If both this field and {@link Transfer#fromRouteId} are defined, the {@link com.krabelard.model.required.Trip#id}
     * must belong to the {@link com.krabelard.model.required.Route#id}, and this field will take precedence.
     */
    String fromTripId;
    /**
     * <code>to_trip_id</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Identifies a trip where a connection between routes ends.
     * <p>
     * If this field is defined, the transfer will apply to the departing trip for the given {@link Transfer#toStopId}.
     * <p>
     * If both this field and {@link Transfer#toRouteId} are defined, the {@link com.krabelard.model.required.Trip#id}
     * must belong to the {@link com.krabelard.model.required.Route#id}, and {@link Transfer#fromTripId} will take precedence.
     * <p>
     * <b>Required</b> for {@link TransferType#InSeat} and {@link TransferType#ReBoard}.
     */
    String toTripId;
    /**
     * <code>transfer_type</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Indicates the type of connections for the specified ({@link Transfer#fromStopId}, {@link Transfer#toStopId}) pair.
     */
    TransferType transferType;
    /**
     * <code>min_transfer_time</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Amount of time, in seconds, that must be available to permit a transfer between routes at the specified stops.
     * The {@link Transfer#minTransferTime} should be sufficient to permit a typical rider to move between the two stops,
     * including buffer time to allow for schedule variance on each route.
     */
    Integer minTransferTime;

    public Optional<String> getFromStopId() {
        return Optional.ofNullable(fromStopId);
    }

    public Optional<String> getToStopId() {
        return Optional.ofNullable(toStopId);
    }

    public Optional<String> getFromRouteId() {
        return Optional.ofNullable(fromRouteId);
    }

    public Optional<String> getToRouteId() {
        return Optional.ofNullable(toRouteId);
    }

    public Optional<String> getFromTripId() {
        return Optional.ofNullable(fromTripId);
    }

    public Optional<String> getToTripId() {
        return Optional.ofNullable(toTripId);
    }

    public Optional<Integer> getMinTransferTime() {
        return Optional.ofNullable(minTransferTime);
    }
}
