package com.krabelard.model.optional.gtfs_fares_v2;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Optional
// Associate with GTFS-Fares V2
@Value
@Builder
public class FareLegRule {
    Long legGroupId;    // Optional
    Long networkId;     // Optional
    Long fromAreaId;    // Optional
    Long toAreaId;      // Optional
    long fareProductId; // Required

    public Optional<Long> getLegGroupId() {
        return Optional.ofNullable(legGroupId);
    }

    public Optional<Long> getNetworkId() {
        return Optional.ofNullable(networkId);
    }

    public Optional<Long> getFromAreaId() {
        return Optional.ofNullable(fromAreaId);
    }

    public Optional<Long> getToAreaId() {
        return Optional.ofNullable(toAreaId);
    }
}
