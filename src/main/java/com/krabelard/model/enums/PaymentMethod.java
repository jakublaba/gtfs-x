package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentMethod {
    OnBoard(0),
    BeforeBoarding(1);

    private final int paymentMethod;
}
