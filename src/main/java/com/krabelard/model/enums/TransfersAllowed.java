package com.krabelard.model.enums;

import com.krabelard.model.optional.gtfs_fares_v1.FareAttribute;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link FareAttribute#transfers}.
 * <br>
 * Valid options are:
 * <li><code>0</code> - No transfers permitted on this fare.</li>
 * <li><code>1</code> - Riders may transfer once.</li>
 * <li><code>2</code> - Riders may transfer twice.</li>
 * <li>empty - Unlimited transfers are permitted</li>
 */
@RequiredArgsConstructor
public enum TransfersAllowed implements Parsable<Integer> {
    NOT_ALLOWED(0),
    ONCE(1),
    TWICE(2),
    // According to GTFS reference, empty field means unlimited transfers
    UNLIMITED(null);

    private final Integer value;

    @Override
    public Integer value() {
        return value;
    }
}
