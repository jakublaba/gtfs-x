package com.krabelard.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.krabelard.model.required.CalendarDate;

/**
 * Representation for {@link CalendarDate#exceptionType}.
 * <br>
 * Valid options are:
 * <li>
 *     <code>1</code> - service has been added for the specified date
 * </li>
 * <li>
 *     <code>2</code> - service has been removed for the specified date
 * </li>
 */
@RequiredArgsConstructor
@Getter
public enum ExceptionType {
    Added(1),
    Removed(2);

    private final int exceptionType;
}
