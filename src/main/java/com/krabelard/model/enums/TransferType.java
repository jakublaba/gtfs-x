package com.krabelard.model.enums;

import lombok.RequiredArgsConstructor;

/**
 * Representation for {@link com.krabelard.model.optional.Transfer#transferType}.
 * <br>
 * Valid options are:
 * <li><code>0</code> or empty - Recommended transfer point between routes.</li>
 * <li><code>1</code> - TImed transfer point between two routes. The departing vehicle is expected to wait for the
 * arriving one and leave sufficient time for a rider to transfer between routes.</li>
 * <li><code>2</code> - Transfer requires a minimum amount of time between arrival and departure to ensure a connection.
 * The time required to transfer is specified by {@link com.krabelard.model.optional.Transfer#minTransferTime}.</li>
 * <li><code>3</code> - Transfers are not possible between routes at the location.</li>
 * <li><code>4</code> - Passengers can transfer from one trip to another by staying onboard the same vehicle
 * (as "in-seat transfer"). More details about this type of transfer can be found <a href=https://gtfs.org/schedule/reference/#linked-trips>here</a>.</li>
 * <li><code>5</code> - In-seat transfers are not allowed between sequential trips. The passenger must alight from the
 * vehicle and re-board. More details about this type of transfer can be found <a href=https://gtfs.org/schedule/reference/#linked-trips>here</a></li>
 */
@RequiredArgsConstructor
public enum TransferType implements Parsable<Integer> {
    RECOMMENDED(0),
    TIMED(1),
    REQUIRES_MINIMUM_TIME(2),
    NOT_POSSIBLE(3),
    IN_SEAT(4),
    RE_BOARD(5);

    private final int value;

    @Override
    public Integer value() {
        return value;
    }
}
