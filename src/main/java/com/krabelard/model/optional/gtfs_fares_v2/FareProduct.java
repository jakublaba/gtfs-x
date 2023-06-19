package com.krabelard.model.optional.gtfs_fares_v2;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

// Optional
// Associated with GTFS-Fares V2
@Value
@Builder
public class FareProduct {
    long id;                    // Required
    String name;                // Optional
    Long fareMediaId;           // Optional
    BigDecimal amount;          // Required
    String currency;            // Required
}
