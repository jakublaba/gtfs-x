package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Direction {
    Outbound(0),
    Inbound(1);

    private final int direction;
}
