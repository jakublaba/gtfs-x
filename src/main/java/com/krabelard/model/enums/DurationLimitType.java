package com.krabelard.model.enums;

import com.krabelard.model.optional.gtfs_fares_v2.FareTransferRule;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link FareTransferRule#durationLimitType}.
 * <br>
 * Valid options are:
 * <li><code>0</code> - Between the departure fare validation of the current leg and the arrival fare validation of the next leg.</li>
 * <li><code>1</code> - Between the departure fare validation of the current leg and the departure fare validation of the next leg.</li>
 * <li><code>2</code> - Between the arrival fare validation of the current leg and the departure fare validation of the next leg.</li>
 * <li><code>3</code> - Between the arrival fare validation of the current leg and the arrival fare validation of the next leg.</li>
 */
@RequiredArgsConstructor
public enum DurationLimitType implements Parsable<Integer> {
    BETWEEN_DEPARTURE_AND_ARRIVAL(0),
    BETWEEN_DEPARTURE_AND_DEPARTURE(1),
    BETWEEN_ARRIVAL_AND_DEPARTURE(2),
    BETWEEN_ARRIVAL_AND_ARRIVAL(3);

    private final int value;

    @Override
    public Integer value() {
        return value;
    }
}
