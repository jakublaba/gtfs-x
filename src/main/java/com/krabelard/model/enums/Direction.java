package com.krabelard.model.enums;

import com.krabelard.model.required.Trip;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link Trip#direction}.
 * <br>
 * Valid options are:
 * <li><code>0</code> - Travel in one direction (e.g. outbound travel)</li>
 * <li><code>1</code> - Travel in the opposite direction (e.g. inbound travel)</li>
 */
@RequiredArgsConstructor
public enum Direction implements Parsable<Integer> {
    OUTBOUND(0),
    INBOUND(1);

    private final int value;

    @Override
    public Integer value() {
        return value;
    }
}
