package com.krabelard.model.optional.gtfs_fares_v2;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from optional <code>fare_leg_rules.txt</code> file.
 * <p>
 * <b>Versions</b>
 * <br>
 * There are two modelling options for describing fares. GTFS-Fares V1 is the legacy option for describing minimal fare information.
 * GTFS-Fares V2 is an updated method that allows for a more detailed account of an agency's fare structure.
 * Both are allowed to be present in a dataset, but only one method should be used by a data consumer for a given dataset.
 * It is recommended that GTFS-Fares V2 takes precedence over GTFS-Fares V1.
 * <p>
 * For full specification and details about processing leg costs, visit <a href="https://gtfs.org/schedule/reference/#fare_leg_rulestxt">the gtfs reference</a>
 */
@Value
@Builder
public class FareLegRule {
    /**
     * <code>leg_group_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies a group of entries.
     * <br>
     * Used to describe a fare transfer rules between {@link FareTransferRule#fromLegGroupId} and {@link FareTransferRule#toLegGroupId}.
     * Multiple {@link FareLegRule} entities may belong to the same {@link FareLegRule#legGroupId}.
     * The same {@link FareLegRule} entity must not belong belong to multiple {@link FareLegRule#legGroupId}.
     */
    Long legGroupId;
    /**
     * <code>network_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies a route network that applies for the fare leg rule.
     * <br>
     * If there are no matching {@link FareLegRule#networkId} values to the <code>networkId</code> being filtered,
     * empty {@link FareLegRule#networkId} will be matched by default.
     */
    Long networkId;
    /**
     * <code>from_area_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies a departure area.
     * <br>
     * If there are no matching {@link FareLegRule#fromAreaId} values to the <code>fromAreaId</code> being filtered,
     * empty {@link FareLegRule#fromAreaId} will be matched by default.
     */
    Long fromAreaId;
    /**
     * <code>to_area_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies an arrival area.
     * <br>
     * If there are not matching {@link FareLegRule#toAreaId} values to the <code>toAreaId</code> being filtered,
     * empty {@link FareLegRule#toAreaId} will be matched by default.
     */
    Long toAreaId;
    /**
     * <code>fare_product_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * The fare product required to travel the leg.
     */
    long fareProductId;

    public Optional<Long> getLegGroupId() {
        return Optional.ofNullable(legGroupId);
    }

    public Optional<Long> getNetworkId() {
        return Optional.ofNullable(networkId);
    }

    public Optional<Long> getFromAreaId() {
        return Optional.ofNullable(fromAreaId);
    }

    public Optional<Long> getToAreaId() {
        return Optional.ofNullable(toAreaId);
    }
}
