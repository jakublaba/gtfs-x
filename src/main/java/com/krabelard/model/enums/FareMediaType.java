package com.krabelard.model.enums;

import com.krabelard.model.optional.gtfs_fares_v2.FareMedia;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link FareMedia#mediaType}.
 * <br>
 * Valid options are:
 * <li><code>0</code> - None. Used when there is no other media involved in purchasing or validating a fare product,
 * such as paying cash to a driver or conductor with no physical ticket provided.</li>
 * <li><code>2</code> - Physical transit card that has stored tickets, passes or monetary value.</li>
 * <li><code>3</code> - cEMV (contactless Europay, Mastercard and Visa) as an open-loop token container for account-based ticketing.</li>
 * <li><code>4</code> - Mobile app that have stored virtual transit cards, tickets, passes, or monetary value.</li>
 */
@RequiredArgsConstructor
@Getter
public enum FareMediaType implements Parsable<Integer> {
    None(0),
    PhysicalTransitCard(2),
    cEMV(3),
    MobileApp(4);

    private final int fareMediaType;

    @Override
    public Integer value() {
        return fareMediaType;
    }
}
