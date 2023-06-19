package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DurationLimitType {
    BetweenDepartureAndArrival(0),
    BetweenDepartureAndDeparture(1),
    BetweenArrivalAndDeparture(2),
    BetweenArrivalAndArrival(3);

    private final int durationLimitType;
}
