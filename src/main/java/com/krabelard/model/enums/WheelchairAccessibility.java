package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum WheelchairAccessibility {
    NoInfo(0),
    Accessible(1),
    NotAccessible(2);

    private final int accessibility;
}
