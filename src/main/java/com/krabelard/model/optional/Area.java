package com.krabelard.model.optional;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Optional
@Value
@Builder
public class Area {
    long id;        // Required
    String name;    // Optional

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
