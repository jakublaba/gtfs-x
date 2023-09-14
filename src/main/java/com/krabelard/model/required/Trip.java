package com.krabelard.model.required;

import com.krabelard.model.enums.BikesAllowed;
import com.krabelard.model.enums.Direction;
import com.krabelard.model.enums.TransferType;
import com.krabelard.model.enums.WheelchairAccessibility;
import com.krabelard.model.optional.Transfer;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from required <code>trips.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#tripstxt">the gtfs reference</a>
 */
@Value
@Builder
public class Trip {
    /**
     * <code>route_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a route.
     */
    String routeId;
    /**
     * <code>service_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a set of dates when service is available for one or more routes.
     */
    String serviceId;
    /**
     * <code>trip_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a trip.
     */
    String id;
    /**
     * <code>trip_headsign</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Text that appears on signage identifying the trip's destination to riders.
     * Should be used to distinguish between different patterns of service on the same route.
     * If the headsign changes during a trip, values for this field may be overridden by {@link StopTime#stopHeadSign}
     * for specific {@link StopTime}s along the trip.
     */
    String headSign;
    /**
     * <code>trip_short_name</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Public facing text used to identify the trip to riders, for instance, to identify a train numbers for commuter rail trips.
     * If riders do not commonly rely on trip names, this field should be empty. Value of this field, if provided, should
     * uniquely identify a trip within a service day; it should not be used for destination names or limited/express designations.
     */
    String shortName;
    /**
     * <code>direction_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Indicates the direction of travel for a trip. This field should not be used in routing; it provides a way to separate
     * trips by direction when publishing time tables.
     */
    Direction direction;
    /**
     * <code>block_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies the block to which the trip belongs. A block consists of single trip or many sequential trips made using
     * the same vehicle, defined by shared service days and this field. This field may have trips with different service days,
     * making distinct blocks. See the <a href=https://gtfs.org/schedule/reference/#example-blocks-and-service-day>example</a>.
     * To provide in-seat transfers information, {@link Transfer} entities or <code>{@link Transfer#transferType}={@link TransferType#InSeat}</code>
     * should be provided instead.
     */
    String blockId;
    /**
     * <code>shape_id</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Identifies a geospatial shape describing the vehicle travel path for a trip.
     * <br>
     * Required if the trip has a continuous pickup or drop-off behavior defined either in {@link Route} or {@link StopTime} entities.
     */
    String shapeId;
    /**
     * <code>wheelchair_accessible</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Indicates wheelchair accessibility.
     */
    WheelchairAccessibility wheelchairAccessible;
    /**
     * <code>bikes_allowed</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Indicates whether bikes are allowed.
     */
    BikesAllowed bikesAllowed;

    public Optional<String> getHeadSign() {
        return Optional.ofNullable(headSign);
    }

    public Optional<String> getShortName() {
        return Optional.ofNullable(shortName);
    }

    public Optional<Direction> getDirection() {
        return Optional.ofNullable(direction);
    }

    public Optional<String> getBlockId() {
        return Optional.ofNullable(blockId);
    }

    public Optional<String> getShapeId() {
        return Optional.ofNullable(shapeId);
    }

    public Optional<WheelchairAccessibility> getWheelchairAccessible() {
        return Optional.ofNullable(wheelchairAccessible);
    }

    public Optional<BikesAllowed> getBikesAllowed() {
        return Optional.ofNullable(bikesAllowed);
    }
}
