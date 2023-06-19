package com.krabelard.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LocationType {
    StopOrPlatform(0),
    Station(1),
    EntranceOrExit(2),
    GenericNode(3),
    BoardingArea(4);

    private final int locationType;
}
