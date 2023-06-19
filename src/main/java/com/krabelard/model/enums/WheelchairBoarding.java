package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum WheelchairBoarding {
    NoInfoOrInherit(0),
    Possible(1),
    NotPossible(2);

    private final int wheelchairBoarding;
}
