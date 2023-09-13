package com.krabelard.model.enums;

import com.krabelard.model.optional.gtfs_fares_v1.FareAttribute;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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

    public static PaymentMethod from(String s) {
        var value = Integer.parseInt(s);
        for (var e : values()) {
            if (e.paymentMethod == value) {
                return e;
            }
        }
        throw new IllegalArgumentException(s + " doesn't map to any PaymentMethod option");
    }
}
