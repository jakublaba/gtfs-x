package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link com.krabelard.model.optional.Translation#tableName}.
 */
@RequiredArgsConstructor
public enum TableName implements Parsable<String> {
    AGENCY("agency"),
    STOPS("stops"),
    ROUTES("routes"),
    TRIPS("trips"),
    STOP_TIMES("stop_times"),
    PATHWAYS("pathways"),
    LEVELS("levels"),
    FEED_INFO("feed_info"),
    ATTRIBUTIONS("attributions");

    private final String value;

    @Override
    public String value() {
        return value;
    }
}
