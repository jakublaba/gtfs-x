package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PathwayMode {
    Walkway(1),
    Stairs(2),
    MovingSidewalk(3),
    Escalator(4),
    Elevator(5),
    FareGate(6),
    ExitGate(7);

    private final int pathwayMode;
}
