package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExceptionType {
    Added(1),
    Removed(2);

    private final int exceptionType;
}
