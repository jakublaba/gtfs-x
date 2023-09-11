package com.krabelard.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.krabelard.model.required.Trip;

/**
 * Representation for {@link Trip#wheelchairAccessible}.
 * <br>
 * Valid options are:
 * <li><code>0</code> or empty - No accessibility information for the trip.</li>
 * <li><code>1</code> - Vehicle being used on this particular trip can accommodate at least one rider in a wheelchair.</li>
 * <li><code>2</code> - No riders in wheelchair can be accommodated on this trip.</li>
 */
@RequiredArgsConstructor
@Getter
public enum WheelchairAccessibility {
    NoInfo(0),
    Accessible(1),
    NotAccessible(2);

    private final int accessibility;
}