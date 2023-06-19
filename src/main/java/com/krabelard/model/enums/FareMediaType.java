package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FareMediaType {
    None(0),
    PhysicalTransitCard(1),
    cEMV(2),
    MobileApp(3);

    private final int fareMediaType;
}
