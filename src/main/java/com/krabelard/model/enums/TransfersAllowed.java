package com.krabelard.model.enums;

import com.krabelard.model.optional.gtfs_fares_v1.FareAttribute;
import lombok.Getter;
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
@Getter
public enum TransfersAllowed implements Parsable<TransfersAllowed, Integer> {
    NotAllowed(0),
    Once(1),
    Twice(2),
    // According to GTFS reference, empty field means unlimited transfers
    // TODO - this case probably will need some special handling in CsvUtil#parseEnum
    Unlimited(null);

    private final Integer transfersAllowed;

    @Override
    public Integer value() {
        return transfersAllowed;
    }
}
