package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ServiceType {
    FrequencyBased(0),
    ScheduleBased(1);

    private final int serviceType;
}
