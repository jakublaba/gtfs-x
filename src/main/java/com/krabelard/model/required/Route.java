package com.krabelard.model.required;

import com.krabelard.model.enums.DropOffType;
import com.krabelard.model.enums.PickupType;
import com.krabelard.model.enums.RouteType;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Required
@Value
@Builder
public class Route {
    long id;                        // Required
    Long agencyId;                  // Conditionally required
    String shortName;               // Conditionally required
    String longName;                // Conditionally required
    String description;             // Optional
    RouteType type;                 // Required
    String url;                     // Optional
    String color;                   // Optional
    Integer sortOrder;              // Optional
    PickupType continuousPickup;    // Optional
    DropOffType continuousDropOff;  // Optional
    Long networkId;                 // Optional

    public Optional<Long> getAgencyId() {
        return Optional.ofNullable(agencyId);
    }

    public Optional<String> getShortName() {
        return Optional.ofNullable(shortName);
    }

    public Optional<String> getLongName() {
        return Optional.ofNullable(longName);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    public Optional<String> getColor() {
        return Optional.ofNullable(color);
    }

    public Optional<Integer> getSortOrder() {
        return Optional.ofNullable(sortOrder);
    }

    public Optional<PickupType> getContinuousPickup() {
        return Optional.ofNullable(continuousPickup);
    }

    public Optional<DropOffType> getContinuousDropOff() {
        return Optional.ofNullable(continuousDropOff);
    }

    public Optional<Long> getNetworkId() {
        return Optional.ofNullable(networkId);
    }
}
