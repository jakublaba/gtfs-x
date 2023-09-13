package com.krabelard.model.optional.gtfs_fares_v1;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from optional <code>fare_rules.txt</code> file.
 * <p>
 * <b>Versions</b>
 * <br>
 * There are two modelling options for describing fares. GTFS-Fares V1 is the legacy option for describing minimal fare information.
 * GTFS-Fares V2 is an updated method that allows for a more detailed account of an agency's fare structure.
 * Both are allowed to be present in a dataset, but only one method should be used by a data consumer for a given dataset.
 * It is recommended that GTFS-Fares V2 takes precedence over GTFS-Fares V1.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#fare_rulestxt">the gtfs reference</a>
 */
@Value
@Builder
public class FareRule {
    /**
     * <code>fare_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a fare class.
     */
    String fareId;
    /**
     * <code>route_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies a route associated with the fare class. If several routes with the same fare attributes exists, create
     * a {@link FareRule} entity for each one.
     */
    String routeId;
    /**
     * <code>origin_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies an origin zone. If a fare class has multiple origin zones, create a {@link FareRule} entity for each one.
     */
    String originId;
    /**
     * <code>destination_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies a destination zone. If a fare class has multiple destination zones, create a {@link FareRule} entity for each one.
     */
    String destinationId;
    /**
     * <code>contains_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies the zones that a rider will enter while using a given fare class. Used in some systems to calculate correct
     * fare class.
     */
    String containsId;

    public Optional<String> getRouteId() {
        return Optional.ofNullable(routeId);
    }

    public Optional<String> getOriginId() {
        return Optional.ofNullable(originId);
    }

    public Optional<String> getDestinationId() {
        return Optional.ofNullable(destinationId);
    }

    public Optional<String> getContainsId() {
        return Optional.ofNullable(containsId);
    }
}
