package com.krabelard.model.enums;

import com.krabelard.model.required.Trip;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link Trip#wheelchairAccessible}.
 * <br>
 * Valid options are:
 * <li><code>0</code> or empty - No accessibility information for the trip.</li>
 * <li><code>1</code> - Vehicle being used on this particular trip can accommodate at least one rider in a wheelchair.</li>
 * <li><code>2</code> - No riders in wheelchair can be accommodated on this trip.</li>
 */
@RequiredArgsConstructor
public enum WheelchairAccessibility implements Parsable<Integer> {
    NO_INFO(0),
    ACCESSIBLE(1),
    NOT_ACCESSIBLE(2);

    private final int value;

    @Override
    public Integer value() {
        return value;
    }
}
