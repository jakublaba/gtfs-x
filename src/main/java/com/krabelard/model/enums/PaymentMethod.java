package com.krabelard.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.krabelard.model.optional.gtfs_fares_v1.FareAttribute;

/**
 * Representation for {@link FareAttribute#paymentMethod}.
 * <br>
 * Valid options are:
 * <li><code>0</code> - Fare is paid on board.</li>
 * <li><code>1</code> - Fare must be paid before boarding.</li>
 */
@RequiredArgsConstructor
@Getter
public enum PaymentMethod {
    OnBoard(0),
    BeforeBoarding(1);

    private final int paymentMethod;
}
