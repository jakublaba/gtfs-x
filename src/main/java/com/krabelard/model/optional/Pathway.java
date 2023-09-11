package com.krabelard.model.optional;

import com.krabelard.model.enums.PathwayMode;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from optional <code></code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#">the gtfs reference</a>
 */
@Value
@Builder
public class Pathway {
    /**
     * <code>pathway_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a pathway. Used by systems as an internal identifier for the record. Must be unique in the dataset.
     */
    long id;
    /**
     * <code>from_stop_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Location at which the pathway begins.
     * <p>
     * Must contain a {@link com.krabelard.model.required.Stop#id} that identifies as {@link com.krabelard.model.enums.LocationType#StopOrPlatform},
     * {@link com.krabelard.model.enums.LocationType#EntranceOrExit}, {@link com.krabelard.model.enums.LocationType#GenericNode}
     * or {@link com.krabelard.model.enums.LocationType#BoardingArea}.
     * <p>
     * Values for {@link com.krabelard.model.enums.LocationType#Station} are forbidden.
     */
    long fromStopId;
    /**
     * <code>to_stop_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Location at which the pathway ends.
     * <p>
     * See {@link Pathway#fromStopId}.
     */
    long toStopId;
    /**
     * <code>pathway_mode</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Type of pathway between the specified {@link Pathway#fromStopId} and {@link Pathway#toStopId}.
     */
    PathwayMode mode;
    /**
     * <code>is_bidirectional</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Indicates the direction that the pathway can be taken:
     * <br>
     * <code>false</code> - Unidirectional pathway that can only be used from {@link Pathway#fromStopId} to {@link Pathway#toStopId}
     * <br>
     * <code>true</code> - Bidirectional pathway that can be used in both directions.
     * <p>
     * {@link PathwayMode#ExitGate}s must not be bidirectional.
     */
    boolean isBidirectional;
    /**
     * <code>length</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Horizontal length in meters of the pathway from the origin location ({@link Pathway#fromStopId}) to the
     * destination location ({@link Pathway#toStopId}).
     * <p>
     * This field is recommended for {@link PathwayMode#Walkway}, {@link PathwayMode#FareGate}s and {@link PathwayMode#ExitGate}s.
     */
    Double length;
    /**
     * <code>traversal_time</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Average time in seconds needed to walk through the pathway from the origin location ({@link Pathway#fromStopId})
     * to the destination location ({@link Pathway#toStopId}).
     * <p>
     * This field is recommended for {@link PathwayMode#MovingSidewalk}s, {@link PathwayMode#Escalator}s and {@link PathwayMode#Elevator}s.
     */
    Integer traversalTime;
    /**
     * <code>stair_count</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Number of stairs of the pathway.
     * <p>
     * A positive value of this field implies that the rider walk up from {@link Pathway#fromStopId} to {@link Pathway#toStopId}.
     * <br>
     * Negative value of this field implies that the rider walk down from {@link Pathway#fromStopId} to {@link Pathway#toStopId}.
     * <p>
     * This field is recommended for {@link PathwayMode#Stairs}.
     * <p>
     * If only an estimated stair count can be provided, it is recommended to approximate 15 stairs for 1 floor.
     */
    Integer stairCount;
    /**
     * <code>max_slope</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Maximum slope ratio of the pathway. Valid options are:
     * <br>
     * <code>0</code> or empty - No slope.
     * <br>
     * <code>float</code> - Slope ratio of the pathway - positive for upwards, negative for downwards.
     * <p>
     * This field should only be used with {@link PathwayMode#Walkway}s and {@link PathwayMode#MovingSidewalk}s.
     */
    Float maxSlope;
    /**
     * <code>min_width</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Minimum width of the pathway in meters.
     * <p>
     * This field is recommended if the minimum width is less than 1 meter.
     */
    Float minWidth;
    /**
     * <code>signposted_as</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Public facing text from physical signage that is visible to riders.
     * <p>
     * May be used to provide text directions to riders, such as 'follow signs to '. The text in this field should appear
     * clearly as it is printed on the signs.
     * <p>
     * When the physical signage is multilingual, this field may be populated and translated following the example of
     * {@link com.krabelard.model.required.Stop#name} in the field definition of {@link FeedInfo#feedLanguage}.
     */
    String signpostedAs;
    /**
     * <code>reverse_signposted_as</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Same as {@link Pathway#signpostedAs}, but when the pathway is used in the opposite direction (from {@link Pathway#toStopId}
     * to {@link Pathway#fromStopId}).
     */
    String reverseSignpostedAs;

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
