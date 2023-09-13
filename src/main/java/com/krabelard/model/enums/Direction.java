package com.krabelard.model.enums;

import com.krabelard.model.required.Trip;
import com.krabelard.util.CsvUtil;
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
public enum Direction {
    Outbound(0),
    Inbound(1);

    private final int direction;

    public static Direction from(String s) {
        var value = CsvUtil.parseNullableInt(s);
        if (value == null) {
            return null;
        }
        for (var e : values()) {
            if (e.direction == value) {
                return e;
            }
        }
        throw new IllegalArgumentException(s + " doesn't map to any Direction option");
    }
}
