package com.krabelard.model.optional.gtfs_fares_v2;

import com.krabelard.model.enums.FareMediaType;
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
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#fare_mediatxt">the gtfs reference</a>
 */
@Value
@Builder
public class FareMedia {
    /**
     * <code>fare_media_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a fare media.
     */
    String id;
    /**
     * <code>fare_media_name</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Name of the fare media. For fare media which are transit cards ({@link FareMediaType#cEMV}) or mobile apps ({@link FareMediaType#MobileApp}),
     * this field should be included and should match the rider-facing name used by the organizations delivering them.
     */
    String name;
    /**
     * <code>fare_media_type</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Type of fare media.
     */
    FareMediaType mediaType;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
