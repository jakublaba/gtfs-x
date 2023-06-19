package com.krabelard.model.optional.gtfs_fares_v2;

import com.krabelard.model.enums.DurationLimitType;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Optional
// Associated with GTFS-Fares V2
@Value
@Builder
public class FareTransferRule {
    Long fromLegGroupId;                    // Optional
    Long toLegGroupId;                      // Optional
    Integer transferCount;                  // Conditionally forbidden
    Integer durationLimit;                  // Optional
    DurationLimitType durationLimitType;    // Conditionally required
    Long fareProductId;                     // Optional

    public Optional<Long> getFromLegGroupId() {
        return Optional.ofNullable(fromLegGroupId);
    }

    public Optional<Long> getToLegGroupId() {
        return Optional.ofNullable(toLegGroupId);
    }

    public Optional<Integer> getTransferCount() {
        return Optional.ofNullable(transferCount);
    }

    public Optional<Integer> getDurationLimit() {
        return Optional.ofNullable(durationLimit);
    }

    public Optional<DurationLimitType> getDurationLimitType() {
        return Optional.ofNullable(durationLimitType);
    }

    public Optional<Long> getFareProductId() {
        return Optional.ofNullable(fareProductId);
    }
}
