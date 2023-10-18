package com.krabelard.model.optional.gtfs_fares_v1;

import com.krabelard.model.enums.PaymentMethod;
import com.krabelard.model.enums.TransfersAllowed;
import com.krabelard.model.required.Agency;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from optional <code>fare_attributes.txt</code> file.
 * <p>
 * <b>Versions</b>
 * <br>
 * There are two modelling options for describing fares. GTFS-Fares V1 is the legacy option for describing minimal fare information.
 * GTFS-Fares V2 is an updated method that allows for a more detailed account of an agency's fare structure.
 * Both are allowed to be present in a dataset, but only one method should be used by a data consumer for a given dataset.
 * It is recommended that GTFS-Fares V2 takes precedence over GTFS-Fares V1.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#fare_attributestxt">the gtfs reference</a>
 */
@Value
@Builder
public class FareAttribute {
    /**
     * <code>fare_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a fare class.
     */
    String fareId;
    /**
     * <code>price</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Fare price, in the unit specified by {@link FareAttribute#currency}.
     */
    double price;
    /**
     * <code>currency_type</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Currency used to pay the fare.
     */
    String currency;
    /**
     * <code>payment_method</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Indicates when the fare must be paid.
     */
    PaymentMethod paymentMethod;
    /**
     * <code>transfers</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Indicates the number of transfers permitted on this fare.
     */
    TransfersAllowed transfers;
    /**
     * <code>agency_id</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Identifies the relevant agency for a fare.
     * <br>
     * Required if multiple {@link Agency} entities are present.
     */
    String agencyId;
    /**
     * <code>transfer_duration</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Length of time in seconds before a transfer expires. When <code>{@link FareAttribute#transfers}={@link TransfersAllowed#NOT_ALLOWED}</code>,
     * this field may be used to indicate how long a ticket is valid for or it may be left empty.
     */
    Integer transferDuration;

    public Optional<String> getAgencyId() {
        return Optional.ofNullable(agencyId);
    }

    public Optional<Integer> getTransferDuration() {
        return Optional.ofNullable(transferDuration);
    }
}
