package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;
import com.krabelard.model.optional.gtfs_fares_v2.FareTransferRule;

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
public enum DurationLimitType {
    BetweenDepartureAndArrival(0),
    BetweenDepartureAndDeparture(1),
    BetweenArrivalAndDeparture(2),
    BetweenArrivalAndArrival(3);

    private final int durationLimitType;
}
