package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ServiceAvailability {
    NotAvailable(0),
    Available(1);

    private final int availability;
}
