package com.krabelard.model.enums;

import com.krabelard.model.required.Route;
import com.krabelard.model.required.StopTime;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link Route#continuousPickup}, {@link StopTime#dropOffType}, {@link StopTime#continuousDropOff}.
 * <br>
 * Valid options are:
 * <li><code>0</code> - Continuous stopping or regularly scheduled drop off.</li>
 * <li><code>1</code> - No continuous stopping or no available drop off.</li>
 * <li><code>2</code> - Must phone agency to arrange (continuous stopping) drop off.</li>
 * <li><code>3</code> - Must coordinate with driver to arrange (continuous stopping) drop off.</li>
 */
@RequiredArgsConstructor
public enum DropOffType implements Parsable<Integer> {
    DROP_OFF(0),
    NO_DROP_OFF(1),
    MUST_PHONE_AGENCY(2),
    MUST_COORDINATE_WITH_DRIVER(3);

    private final int value;

    @Override
    public Integer value() {
        return value;
    }
}
