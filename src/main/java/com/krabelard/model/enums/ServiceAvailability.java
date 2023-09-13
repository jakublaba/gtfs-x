package com.krabelard.model.enums;

import com.krabelard.model.required.Calendar;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link Calendar#monday}-{@link Calendar#sunday}.
 * <br>
 * Valid options are:
 * <li>
 * <code>0</code> - service is not available for all specified days of week in the range
 * </li>
 * <li>
 * <code>1</code> - service is available for all specified days of week in the range
 * </li>
 */
@RequiredArgsConstructor
public enum ServiceAvailability implements Parsable<Integer> {
    NotAvailable(0),
    Available(1);

    private final int availability;

    @Override
    public Integer value() {
        return availability;
    }
}
