package com.krabelard.model.enums;

import com.krabelard.model.required.StopTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link StopTime#timePoint}.
 * <br>
 * Valid options are:
 * <li><code>0</code> - Times are considered approximate.</li>
 * <li><code>1</code> or empty - Times are considered exact.</li>
 */
@RequiredArgsConstructor
@Getter
public enum TimePoint {
    Approximate(0),
    Exact(1);

    private final int timePoint;

    public static TimePoint from(Integer value) {
        if (value == null) {
            return null;
        }
        for (var e : values()) {
            if (e.timePoint == value) {
                return e;
            }
        }
        throw new IllegalArgumentException(value + " doesn't map to any Timepoint option");
    }
}
