package com.krabelard.model.enums;

import com.krabelard.model.required.Stop;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link Stop#wheelchairBoarding}.
 * <br>
 * Valid options are:
 * <br>
 * For parentless stops:
 * <li><code>0</code> or empty - No accessibility information for the stop.</li>
 * <li><code>1</code> - Some vehicles at this stop can be boarded by a rider in a wheelchair.</li>
 * <li><code>2</code> - Wheelchair boarding is not possible at this stop.</li>
 * For child stops:
 * <li><code>0</code> or empty - Stop will inherit its {@link WheelchairBoarding} behavior from the parent station, if specified.</li>
 * <li><code>1</code> - There exists some accessible path from outside the station to the specific stop/platform.</li>
 * <li><code>2</code> - There exists no accessible path from the outside to the specific stop/platform.</li>
 * For station entrances/exits:
 * <li><code>0</code> or empty - Station entrance will inherit its {@link WheelchairBoarding} behavior from the parent station, if specified.</li>
 * <li><code>1</code> - Station entrance is wheelchair accessible.</li>
 * <li><code>2</code> - No accessible path from station entrance to stops/platforms.</li>
 */
@RequiredArgsConstructor
public enum WheelchairBoarding implements Parsable<WheelchairBoarding, Integer> {
    NoInfoOrInherit(0),
    Possible(1),
    NotPossible(2);

    private final int wheelchairBoarding;

    @Override
    public Integer value() {
        return wheelchairBoarding;
    }
}
