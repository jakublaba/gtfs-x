package com.krabelard.model.required;

import com.krabelard.model.enums.LocationType;
import com.krabelard.model.enums.WheelchairBoarding;
import com.krabelard.model.optional.Translation;
import com.krabelard.model.optional.gtfs_fares_v1.FareRule;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from required <code>stops.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#stopstxt">the gtfs reference</a>
 */
@Value
@Builder
public class Stop {
    /**
     * <code>stop_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a location: stop/platform, station, entrance/exit, generic node or boarding area.
     * Multiple routes may use the same {@link Stop#id}
     */
    String id;
    /**
     * <code>stop_code</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Short text or a number that identifies the location for riders. These codes are often used in phone-based transit
     * information systems or printed on signage to make it easier for riders to get information for a particular location.
     * The {@link Stop#code} may be the same as {@link Stop#id} if it is public facing. This field should be left empty
     * for locations without a code presented to riders.
     */
    String code;
    /**
     * <code>stop_name</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Name of the location. The {@link Stop#name} should match the agency's rider-facing name for the location as printed
     * on a timetable, published online, or represented on signage. For translations, use {@link Translation} entities.
     * <br>
     * Required for locations which are stops ({@link LocationType#STOP_OR_PLATFORM}), stations ({@link LocationType#STATION})
     * or entrances/exits ({@link LocationType#ENTRANCE_OR_EXIT}).
     */
    String name;
    /**
     * <code>tts_stop_name</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Readable version of the {@link Stop#name}.
     * See "Text-to-speech field" in <a href=https://gtfs.org/schedule/reference/#term-definitions>term definitions</a> for more
     */
    String ttsName;
    /**
     * <code>stop_desc</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Description of the location that provides useful, quality information.
     * Should not be a duplicate of {@link Stop#name}
     */
    String description;
    /**
     * <code>stop_lat</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Latitude of the location.
     * <br>
     * For stops/platform ({@link LocationType#STOP_OR_PLATFORM}) and boarding areas ({@link LocationType#BOARDING_AREA}),
     * the coordinates must be the ones of the bus pole - if exists - and otherwise of where the travelers are boarding
     * the vehicle (on the sidewalk or the platform, and not on the roadway or the track where the vehicle stops).
     * <br>
     * Required for locations which are stops ({@link LocationType#STOP_OR_PLATFORM}), stations ({@link LocationType#STATION})
     * or entrances/exits ({@link LocationType#ENTRANCE_OR_EXIT}).
     */
    Double latitude;
    /**
     * <code>stop_lon</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Longitude of the location.
     * <br>
     * For stops/platform ({@link LocationType#STOP_OR_PLATFORM}) and boarding areas ({@link LocationType#BOARDING_AREA}),
     * the coordinates must be the ones of the bus pole - if exists - and otherwise of where the travelers are boarding
     * the vehicle (on the sidewalk or the platform, and not on the roadway or the track where the vehicle stops).
     * <br>
     * Required for locations which are stops ({@link LocationType#STOP_OR_PLATFORM}), stations ({@link LocationType#STATION})
     * or entrances/exits ({@link LocationType#ENTRANCE_OR_EXIT}).
     */
    Double longitude;
    /**
     * <code>zone_id</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Identifies the fare zone for a stop. If this record represents a station or station entrance, this field is ignored.
     * <br>
     * Required if providing fare information using {@link FareRule} entities.
     */
    String zoneId;
    /**
     * <code>stop_url</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * URL of a web page about the location. Should be different from {@link Agency#url} and {@link Route#url}.
     */
    String url;
    /**
     * <code>location_type</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Location type.
     */
    LocationType locationType;
    /**
     * <code>parent_station</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Defines a hierarchy between the different locations defined by {@link Stop} entities. It contains the ID of the parent location, as followed:
     * <li>{@link LocationType#STOP_OR_PLATFORM} - contains ID of a parent station.</li>
     * <li>{@link LocationType#STATION} - this field must be empty.</li>
     * <li>{@link LocationType#ENTRANCE_OR_EXIT} or {@link LocationType#GENERIC_NODE} - contains ID of a parent station.</li>
     * <li>{@link LocationType#BOARDING_AREA} - contains ID of a parent platform.</li>
     */
    String parentId;
    /**
     * <code>stop_timezone</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Timezone of the location. If the location has a parent station, it inherits the parent station's timezone instead
     * of applying its own. Stations and parentless stops with empty {@link Stop#timezone} inherit the timezone specified
     * by {@link Agency#timezone}. If this field is present, times in {@link StopTime} entities should be entered as the time
     * since midnight in the timezone specified by {@link Agency#timezone}. This ensures that the time values in a trip always
     * increase over the course of a trip, regardless of which timezones the trip crosses.
     */
    String timezone;
    /**
     * <code>wheelchair_boarding</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Indicates whether wheelchair boardings are possible from the location.
     */
    WheelchairBoarding wheelchairBoarding;
    /**
     * <code>level_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Level of the location. The same level may be used by multiple unlinked stations.
     */
    String levelId;
    /**
     * <code>platform_code</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Platform identifier for a platform stop (a stop belonging to a station). This should be just a platform identifier (eg. "G" or "3").
     * Words like "platform" or "track" (or the feed's language-specific equivalent) should not be included.
     * This allows feed consumers to more easily internationalize and localize the platform identifier into other languages.
     */
    String platformCode;

    public Optional<String> getCode() {
        return Optional.ofNullable(code);
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getTtsName() {
        return Optional.ofNullable(ttsName);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Optional<Double> getLatitude() {
        return Optional.ofNullable(latitude);
    }

    public Optional<Double> getLongitude() {
        return Optional.ofNullable(longitude);
    }

    public Optional<String> getZoneId() {
        return Optional.ofNullable(zoneId);
    }

    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    public Optional<String> getParentId() {
        return Optional.ofNullable(parentId);
    }

    public Optional<String> getTimezone() {
        return Optional.ofNullable(timezone);
    }

    public Optional<String> getLevelId() {
        return Optional.ofNullable(levelId);
    }

    public Optional<String> getPlatformCode() {
        return Optional.ofNullable(platformCode);
    }
}
