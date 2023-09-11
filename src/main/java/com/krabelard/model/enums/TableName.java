package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link com.krabelard.model.optional.Translation#tableName}.
 */
@RequiredArgsConstructor
public enum TableName {
    Agency("agency"),
    Stops("stops"),
    Routes("routes"),
    Trips("trips"),
    StopTimes("stop_times"),
    Pathways("pathways"),
    Levels("levels"),
    FeedInfo("feed_info"),
    Attributions("attributions");

    private final String tableName;
}
