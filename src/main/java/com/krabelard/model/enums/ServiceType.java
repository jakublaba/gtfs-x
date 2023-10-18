package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link com.krabelard.model.optional.Frequency#exactTimes}.
 * <br>
 * Valid options are:
 * <li><code>0</code> or empty - Frequency-based trips.</li>
 * <li><code>1</code> - Schedule-based trips with the exact same headway throughout the day.
 * In this case the {@link com.krabelard.model.optional.Frequency#endTime} value must be greater than the last desired
 * trip {@link com.krabelard.model.optional.Frequency#startTime} but less than the last desired trip
 * {@link com.krabelard.model.optional.Frequency#startTime} + {@link com.krabelard.model.optional.Frequency#headwaySecs}</li>
 */
@RequiredArgsConstructor
public enum ServiceType implements Parsable<Integer> {
    FREQUENCY_BASED(0),
    SCHEDULE_BASED(1);

    private final int value;

    @Override
    public Integer value() {
        return value;
    }
}
