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
    NOT_AVAILABLE(0),
    AVAILABLE(1);

    private final int value;

    @Override
    public Integer value() {
        return value;
    }
}
