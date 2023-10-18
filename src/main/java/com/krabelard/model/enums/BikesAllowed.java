package com.krabelard.model.enums;

import com.krabelard.model.required.Trip;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link Trip#bikesAllowed}.
 * <br>
 * Valid options are:
 * <li><code>0</code> or empty - No bike information for the trip.</li>
 * <li><code>1</code> - Vehicle being used on this particular trip can accommodate at least one bicycle.</li>
 * <li><code>2</code> - No bicycles are allowed on this trip.</li>
 */
@RequiredArgsConstructor
public enum BikesAllowed implements Parsable<Integer> {
    NO_INFO(0),
    ALLOWED(1),
    NOT_ALLOWED(2);

    private final int value;

    public Integer value() {
        return value;
    }
}
