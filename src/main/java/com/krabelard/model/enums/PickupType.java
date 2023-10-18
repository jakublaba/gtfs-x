package com.krabelard.model.enums;

import com.krabelard.model.required.Route;
import com.krabelard.model.required.StopTime;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link Route#continuousPickup}, {@link StopTime#pickupType}, {@link StopTime#continuousPickup}.
 * <br>
 * Valid options are:
 * <li><code>0</code> - Continuous stopping or regularly scheduled pickup.</li>
 * <li><code>1</code> - No continuous stopping or no available pickup.</li>
 * <li><code>2</code> - Must phone agency to arrange (continuous stopping) pickup.</li>
 * <li><code>3</code> - Must coordinate with driver to arrange (continuous stopping) pickup.</li>
 */
@RequiredArgsConstructor
public enum PickupType implements Parsable<Integer> {
    PICKUP(0),
    NO_PICKUP(1),
    MUST_PHONE_AGENCY(2),
    MUST_COORDINATE_WITH_DRIVER(3);

    private final int value;

    @Override
    public Integer value() {
        return value;
    }
}
