package com.krabelard.model.optional.gtfs_fares_v2;

import com.krabelard.model.enums.DurationLimitType;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from optional <code>fare_transfer_rules.txt</code> file.
 * <p>
 * <b>Versions</b>
 * <br>
 * There are two modelling options for describing fares. GTFS-Fares V1 is the legacy option for describing minimal fare information.
 * GTFS-Fares V2 is an updated method that allows for a more detailed account of an agency's fare structure.
 * Both are allowed to be present in a dataset, but only one method should be used by a data consumer for a given dataset.
 * It is recommended that GTFS-Fares V2 takes precedence over GTFS-Fares V1.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#fare_transfer_rulestxt">the gtfs reference</a>
 */
@Value
@Builder
public class FareTransferRule {
    /**
     * <code>from_leg_group_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies a group of pre-transfer fare leg rules.
     * <p>
     * If there are no matching {@link FareTransferRule#fromLegGroupId} values to the <code>leg_group_id</code> being filtered,
     * empty values will be matched by default.
     * <p>
     * An empty entry in this field corresponds to all leg groups defined under {@link FareLegRule#legGroupId} excluding
     * the ones listed under this field.
     */
    String fromLegGroupId;
    /**
     * <code>to_leg_group_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies a group of post-transfer fare leg rules.
     * <p>
     * If there are no matching {@link FareTransferRule#toLegGroupId} values to the <code>leg_group_id</code> being filtered,
     * empty values will be matched by default.
     * <p>
     * An empty entry in this field corresponds to all leg groups defined under {@link FareLegRule#legGroupId} excluding
     * the ones listed under this field.
     */
    String toLegGroupId;
    /**
     * <code>transfer_count</code>
     * <p>
     * <b>Conditionally forbidden</b>
     * <p>
     * Defines how many consecutive transfers the transfer rule may be applied to.
     * <br>
     * Valid options are
     * <ul>
     *  <li><code>-1</code> - No limit.</li>
     *  <li><code>1</code> or more - Defines how many transfers the transfer rule may span.</li>
     * </ul>
     * If a sub-journey matches multiple records with different {@link FareTransferRule#transferCount}s, then the minumum
     * value of this field that is greater or equal to the current transfer count on the sub-journey is to be selected.
     * <p>
     * Forbidden if {@link FareTransferRule#fromLegGroupId} is different than {@link FareTransferRule#toLegGroupId}.
     * <br>
     * Required otherwise
     */
    Integer transferCount;
    /**
     * <code>duration_limit</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Defines the duration limit of the transfer. Must be expressed in integer increments of seconds.
     * If there is no duration limit, this field must be left empty.
     */
    Integer durationLimit;
    /**
     * <code>duration_limit_type</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Defines the relative start and end of {@link FareTransferRule#durationLimit}.
     * <p>
     * Required if {@link FareTransferRule#durationLimit} is defined.
     * <br>
     * Forbidden otherwise.
     */
    DurationLimitType durationLimitType;
    /**
     * <code>fare_product_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * The fare product required to transfer between two fare legs. If empty, the cost of the transfer rule is 0.
     */
    String fareProductId;

    public Optional<String> getFromLegGroupId() {
        return Optional.ofNullable(fromLegGroupId);
    }

    public Optional<String> getToLegGroupId() {
        return Optional.ofNullable(toLegGroupId);
    }

    public Optional<Integer> getTransferCount() {
        return Optional.ofNullable(transferCount);
    }

    public Optional<Integer> getDurationLimit() {
        return Optional.ofNullable(durationLimit);
    }

    public Optional<DurationLimitType> getDurationLimitType() {
        return Optional.ofNullable(durationLimitType);
    }

    public Optional<String> getFareProductId() {
        return Optional.ofNullable(fareProductId);
    }
}
