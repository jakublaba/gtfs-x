package com.krabelard.model.enums;

import com.krabelard.model.required.Trip;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link Trip#direction}.
 * <br>
 * Valid options are:
 * <li><code>0</code> - Travel in one direction (e.g. outbound travel)</li>
 * <li><code>1</code> - Travel in the opposite direction (e.g. inbound travel)</li>
 */
@RequiredArgsConstructor
@Getter
public enum Direction implements Parsable<Direction, Integer> {
    Outbound(0),
    Inbound(1);

    private final int direction;

    @Override
    public Integer value() {
        return direction;
    }
}
