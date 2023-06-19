package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PickupType {
    Pickup(0),
    NoPickup(1),
    MustPhoneAgency(2),
    MustCoordinateWithDriver(3);

    private final int continuousPickup;
}
