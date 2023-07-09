package com.krabelard.model.enums;

import com.krabelard.model.required.Route;
import com.krabelard.model.required.StopTime;
import lombok.Getter;
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
@Getter
public enum DropOffType {
    DropOff(0),
    NoDropOff(1),
    MustPhoneAgency(2),
    MustCoordinateWithDriver(3);

    private final int pickupType;
}
