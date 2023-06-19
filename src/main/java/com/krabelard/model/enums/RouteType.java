package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RouteType {
    LightRail(0),
    UndergroundRail(1),
    Rail(2),
    Bus(3),
    Ferry(4),
    CableTram(5),
    AerialLift(6),
    Funicular(7),
    Trolleybus(11),
    Monorail(12);

    private final int routeType;
}
