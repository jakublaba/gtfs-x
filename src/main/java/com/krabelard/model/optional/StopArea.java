package com.krabelard.model.optional;


import lombok.Builder;
import lombok.Value;

// Optional
@Value
@Builder
public class StopArea {
    long areaId;    // Required
    long stopId;    // Required
}
