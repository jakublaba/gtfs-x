package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link com.krabelard.model.optional.Pathway#mode}.
 * <br>
 * Valid options are:
 * <li>1<code></code> or empty - Walkway</li>
 * <li>2<code></code> - Stairs</li>
 * <li>3<code></code> - Moving Sidewalk/Travelator</li>
 * <li>4<code></code> - Escalator</li>
 * <li>5<code></code> - Elevator</li>
 * <li>6<code></code> - Fare Gate (or Payment Gate): A pathway that crosses into an area of the station where proof of
 * payment is required to cross. Fare gates may separate paid areas of the station from unpaid ones, or separate
 * different payment areas within the same station from each other. This information can be used to avoid routing
 * passengers through stations using shortcuts that would require them to make unnecessary payments, like directing
 * a passenger through a subway platform to reach a busway.</li>
 * <li>7<code></code> - Exit Gate: A pathway exiting a paid area into an unpaid area where proof of payment is not
 * required to cross.</li>
 */
@RequiredArgsConstructor
public enum PathwayMode implements Parsable<Integer> {
    WALKWAY(1),
    STAIRS(2),
    MOVING_SIDEWALK(3),
    ESCALATOR(4),
    ELEVATOR(5),
    FARE_GATE(6),
    EXIT_GATE(7);

    private final int value;

    @Override
    public Integer value() {
        return value;
    }
}
