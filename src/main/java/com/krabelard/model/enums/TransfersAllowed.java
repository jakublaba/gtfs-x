package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransfersAllowed {
    NotAllowed(0),
    Once(1),
    Twice(2),
    // Note - gtfs specification says that empty field means unlimited transfers, so this has to be mapped on library level
    Unlimited(3);

    private final int transfersAllowed;
}
