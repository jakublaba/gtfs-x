package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BikesAllowed {
    NoInfo(0),
    Allowed(1),
    NotAllowed(2);

    private final int bikesAllowed;
}
