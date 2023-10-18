package com.krabelard.model.enums;

import com.krabelard.model.required.StopTime;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link StopTime#timePoint}.
 * <br>
 * Valid options are:
 * <li><code>0</code> - Times are considered approximate.</li>
 * <li><code>1</code> or empty - Times are considered exact.</li>
 */
@RequiredArgsConstructor
public enum TimePoint implements Parsable<Integer> {
    APPROXIMATE(0),
    EXACT(1);

    private final int value;

    @Override
    public Integer value() {
        return value;
    }
}
