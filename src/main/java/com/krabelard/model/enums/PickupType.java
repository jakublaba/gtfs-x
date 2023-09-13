package com.krabelard.model.enums;

import com.krabelard.model.required.Route;
import com.krabelard.model.required.StopTime;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
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
@Getter
public enum PickupType {
    Pickup(0),
    NoPickup(1),
    MustPhoneAgency(2),
    MustCoordinateWithDriver(3);

    private final int pickupType;

    public static PickupType from(String s) {
        var value = CsvUtil.parseNullableInt(s);
        if (value == null) {
            return null;
        }
        for (var e : values()) {
            if (e.pickupType == value) {
                return e;
            }
        }
        throw new IllegalArgumentException(s + " doesn't map to any PickupType option");
    }
}
