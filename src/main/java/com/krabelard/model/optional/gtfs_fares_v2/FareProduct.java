package com.krabelard.model.optional.gtfs_fares_v2;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

/**
 * Maps an entry from optional <code>fare_products.txt</code> file.
 * <p>
 * <b>Versions</b>
 * <br>
 * There are two modelling options for describing fares. GTFS-Fares V1 is the legacy option for describing minimal fare information.
 * GTFS-Fares V2 is an updated method that allows for a more detailed account of an agency's fare structure.
 * Both are allowed to be present in a dataset, but only one method should be used by a data consumer for a given dataset.
 * It is recommended that GTFS-Fares V2 takes precedence over GTFS-Fares V1.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#fare_productstxt">the gtfs reference</a>
 */
@Value
@Builder
public class FareProduct {
    /**
     * <code>fare_product_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a fare product.
     */
    long id;
    /**
     * <code>fare_product_name</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * The name of the fare product as displayed to riders.
     */
    String name;
    /**
     * <code>fare_media_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies a fare media that can be employed to use the fare product during the trip. When this field is empty,
     * it is considered that the fare media is unknown.
     */
    Long fareMediaId;
    /**
     * <code>amount</code>
     * <p>
     * <b>Required</b>
     * <p>
     * The cost of the fare product. May be negative to represent transfer discounts. May be zero to represent a fare
     * product that is free.
     */
    BigDecimal amount;
    /**
     * <code>currency</code>
     * <p>
     * <b>Required</b>
     * <p>
     * The currency of the cost of the fare product.
     */
    String currency;
}
