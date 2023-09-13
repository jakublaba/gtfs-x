package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;
import com.krabelard.model.required.Route;

/**
 * Representation for {@link Route#type}.
 * <br>
 * Valid options are:
 * <li><code>0</code> - Tram, Streetcar, Light rail. Any light rail or street level system within a metropolitan area.</li>
 * <li><code>1</code> - Subway, Metro. Any underground rail system within a metropolitan area.</li>
 * <li><code>2</code> - Rail. Used for intercity or long-distance travel.</li>
 * <li><code>3</code> - Bus. Used for short- and long-distance bus routes.</li>
 * <li><code>4</code> - Ferry. Used for short- and long-distance boat service.</li>
 * <li><code>5</code> - Cable tram. Used for street-level rail cars where the cable runs beneath the vehicle (e.g., cable car in San Francisco).</li>
 * <li><code>6</code> - Aerial lift, suspended cable car (e.g., gondola lift, aerial tramway). Cable transport where cabins, cars, gondolas or open chairs are suspended by means of one or more cables.</li>
 * <li><code>7</code> - Funicular. Any rail system designed for steep inclines.</li>
 * <li><code>11</code> - Trolleybus. Electric buses that draw power from overhead wires using poles.</li>
 * <li><code>12</code> - Monorail. Railway in which the track consists of a single rail or a beam.</li>
 */
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

    public static RouteType from(String s) {
        var value = Integer.parseInt(s);
        for (var e : values()) {
            if (e.routeType == value) {
                return e;
            }
        }
        throw new IllegalArgumentException(s + " doesn't map to any RouteType option");
    }
}
