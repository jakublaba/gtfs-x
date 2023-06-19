package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TimePoint {
    Approximate(0),
    Exact(1);

    private final int timePoint;
}
