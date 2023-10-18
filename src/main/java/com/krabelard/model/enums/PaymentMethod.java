package com.krabelard.model.enums;

import com.krabelard.model.optional.gtfs_fares_v1.FareAttribute;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link FareAttribute#paymentMethod}.
 * <br>
 * Valid options are:
 * <li><code>0</code> - Fare is paid on board.</li>
 * <li><code>1</code> - Fare must be paid before boarding.</li>
 */
@RequiredArgsConstructor
public enum PaymentMethod implements Parsable<Integer> {
    ON_BOARD(0),
    BEFORE_BOARDING(1);

    private final int value;

    @Override
    public Integer value() {
        return value;
    }
}
