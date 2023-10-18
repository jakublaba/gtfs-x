package com.krabelard.model.optional;


import lombok.Builder;
import lombok.Value;

/**
 * Maps an entry from optional <code>stop_areas.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#stop_areastxt">the gtfs reference</a>
 */
@Value
@Builder
public class StopArea {
    /**
     * <code>area_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies an area to which multiple {@link com.krabelard.model.required.Stop#id}s belong.
     * The same {@link com.krabelard.model.required.Stop#id} may be defined in many {@link StopArea#areaId}s.
     */
    String areaId;
    /**
     * <code>stop_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a stop. If a station (i.e. a stop with {@link com.krabelard.model.enums.LocationType#STATION} is defined
     * in this field, it is assumed that all of its platforms (i.e. all stops with {@link com.krabelard.model.enums.LocationType#STOP_OR_PLATFORM}
     * that have this station defined as {@link com.krabelard.model.required.Stop#parentId}) are part of the same area.
     * This behavior can be overridden by assigning platforms to other areas.
     */
    String stopId;
}
