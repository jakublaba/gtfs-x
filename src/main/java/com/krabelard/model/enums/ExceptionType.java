package com.krabelard.model.enums;

import com.krabelard.model.required.CalendarDate;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link CalendarDate#exceptionType}.
 * <br>
 * Valid options are:
 * <li>
 * <code>1</code> - service has been added for the specified date
 * </li>
 * <li>
 * <code>2</code> - service has been removed for the specified date
 * </li>
 */
@RequiredArgsConstructor
public enum ExceptionType implements Parsable<Integer> {
    ADDED(1),
    REMOVED(2);

    private final int value;

    @Override
    public Integer value() {
        return value;
    }
}
