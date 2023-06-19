package com.krabelard.model.required;

import com.krabelard.model.enums.LocationType;
import com.krabelard.model.enums.WheelchairBoarding;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Required
@Value
@Builder
public class Stop {
    long id;                                // Required
    String code;                            // Optional
    String name;                            // Conditionally required
    String ttsName;                         // Optional
    String description;                     // Optional
    Double latitude;                        // Conditionally required
    Double longitude;                       // Conditionally required
    Long zoneId;                            // Conditionally required
    String url;                             // Optional
    LocationType locationType;              // Optional in gtfs, defaults to 0
    Long parentId;                          // Conditionally required
    String timezone;                        // Optional
    WheelchairBoarding wheelchairBoarding;  // Optional in gtfs, defaults to 0
    Long levelId;                           // Optional
    String platformCode;                    // Optional

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

    public Optional<Long> getZoneId() {
        return Optional.ofNullable(zoneId);
    }

    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    public Optional<Long> getParentId() {
        return Optional.ofNullable(parentId);
    }

    public Optional<String> getTimezone() {
        return Optional.ofNullable(timezone);
    }

    public Optional<Long> getLevelId() {
        return Optional.ofNullable(levelId);
    }

    public Optional<String> getPlatformCode() {
        return Optional.ofNullable(platformCode);
    }
}
