package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FareTransferType {
    FROM_PLUS_CURRENT(0),
    ALL(1),
    CURRENT(2);

    private final int value;
}
