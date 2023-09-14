package com.krabelard.model.optional;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from optional <code>shapes.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#shapestxt">the gtfs reference</a>
 */
@Value
@Builder
public class Shape {
    /**
     * <code>shape_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a shape.
     */
    String id;
    /**
     * <code>shape_pt_lat</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Latitude of a shape point. Each {@link Shape} entity represents a shape point used to define a shape.
     */
    double ptLatitude;
    /**
     * <code>shape_pt_lon</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Longitude of a shape point.
     */
    double ptLongitude;
    /**
     * <code>shape_pt_sequence</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Sequence in which the shape points connect to form the shape. Values must increase along the trip but do not
     * need to be consecutive.
     */
    int ptSequence;
    /**
     * <code>shape_dist_traveled</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Actual distance traveled along the shape from the first shape point to the point specified in this record.
     * Used by trip planners to show the correct portion of the shape on a map. Values must increase along with {@link Shape#ptSequence};
     * they must not be used to show reverse travel along a route. Distance units must be consistent with those used
     * in {@link com.krabelard.model.required.StopTime} entities.
     * <p>
     * Recommended for routes that have looping or inlining (the vehicle crosses or travels over the same portion of alignment in one trip).
     * <p>
     * If a vehicle retraces or crosses the route alignment at points in the course of a trip, this field is important to
     * clarify how portions of the points in {@link Shape} entities correspond with {@link com.krabelard.model.required.StopTime} entities.
     */
    Double distTraveled;

    public Optional<Double> getDistTraveled() {
        return Optional.ofNullable(distTraveled);
    }
}
