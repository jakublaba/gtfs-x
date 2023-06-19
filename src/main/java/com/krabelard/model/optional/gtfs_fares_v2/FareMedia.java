package com.krabelard.model.optional.gtfs_fares_v2;

import com.krabelard.model.enums.FareMediaType;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Optional
// Associated with GTFS-Fares V2
@Value
@Builder
public class FareMedia {
    long id;                    // Required
    String name;                // Optional
    FareMediaType mediaType;    // Required

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
