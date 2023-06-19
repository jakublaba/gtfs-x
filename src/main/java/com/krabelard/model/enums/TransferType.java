package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransferType {
    Recommended(0),
    Timed(1),
    RequiresMinimumTime(2),
    NotPossible(3),
    InSeat(4),
    ReBoard(5);

    private final int transferType;
}
