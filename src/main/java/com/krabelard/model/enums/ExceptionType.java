package com.krabelard.model.enums;

import com.krabelard.model.required.CalendarDate;
import lombok.Getter;
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
@Getter
public enum ExceptionType {
    Added(1),
    Removed(2);

    private final int exceptionType;

    public static ExceptionType from(String s) {
        var value = Integer.parseInt(s);
        for (var e : values()) {
            if (e.exceptionType == value) {
                return e;
            }
        }
        throw new IllegalArgumentException(s + " doesn't map to any ExceptionType option");
    }
}
