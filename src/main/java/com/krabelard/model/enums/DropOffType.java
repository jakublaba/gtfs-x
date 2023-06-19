package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DropOffType {
    DropOff(0),
    NoDropOff(1),
    MustPhoneAgency(2),
    MustCoordinateWithDriver(3);

    private final int continuousPickup;
}
