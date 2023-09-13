package com.krabelard.model.optional;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from optional <code>areas.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#areastxt">the gtfs reference</a>
 */
@Value
@Builder
public class Area {
    /**
     * <code>area_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies an area. Unique.
     */
    String id;
    /**
     * <code>agency_name</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * The name of the area as displayed to the rider.
     */
    String name;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
