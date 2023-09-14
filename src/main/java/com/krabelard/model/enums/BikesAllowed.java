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
    NoInfo(0),
    Allowed(1),
    NotAllowed(2);

    private final int bikesAllowed;

    public Integer value() {
        return bikesAllowed;
    }
}
