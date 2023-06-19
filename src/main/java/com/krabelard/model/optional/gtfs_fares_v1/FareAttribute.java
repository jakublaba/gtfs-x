package com.krabelard.model.optional.gtfs_fares_v1;

import com.krabelard.model.enums.PaymentMethod;
import com.krabelard.model.enums.TransfersAllowed;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Optional
// Associated with GTFS-Fares V1
@Value
@Builder
public class FareAttribute {
    long id;                        // Required
    double price;                   // Required
    String currency;                // Required
    PaymentMethod paymentMethod;    // Required
    TransfersAllowed transfers;     // Required
    Long agencyId;                  // Conditionally required
    Integer transferDuration;       // Optional

    public Optional<Long> getAgencyId() {
        return Optional.ofNullable(agencyId);
    }

    public Optional<Integer> getTransferDuration() {
        return Optional.ofNullable(transferDuration);
    }
}
