package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FareTransferType {
    FromPlusCurrent(0),
    All(1),
    Current(2);

    private final int fareTransferType;
}
