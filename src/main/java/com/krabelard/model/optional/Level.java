package com.krabelard.model.optional;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from optional <code>levels.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#levelstxt">the gtfs reference</a>
 */
@Value
@Builder
public class Level {
    /**
     * <code>level_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a level in a station.
     */
    long id;
    /**
     * <code>level_index</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Numeric index of the level that indicates its relative position.
     * Ground level should have index <code>0</code>, with levels above ground indicated by positive indices and levels
     * below ground by negative indices.
     */
    float index;
    /**
     * <code>level_name</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Name of the level as seen by the rider inside the building or station.
     */
    String name;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
