package com.krabelard.model.enums;

import com.krabelard.model.optional.Pathway;
import com.krabelard.model.required.Stop;
import lombok.AllArgsConstructor;

/**
 * Representation for {@link Stop#locationType}.
 * <br>
 * Valid options are:
 * <li><code>0</code> or blank - <b>Stop</b> (or a <b>Platform</b>). A location where passengers board and disembark from a transit vehicle.</li>
 * <li><code>1</code> - <b>Station</b>. A physical structure or area that contains one or more platform.</li>
 * <li><code>2</code> - <b>Entrance/Exit</b>. A location where passengers can enter or exit a station from the street.
 * If an entrance/exit belongs to multiple stations, it may be linked by pathways to both, but the data provider must pick one of them as parent.</li>
 * <li><code>3</code> - <b>Generic Node</b>. A location within a station, not matching any other {@link LocationType}, that may be
 * used to link together pathways defined with {@link Pathway} entities</li>
 * <li><code>4</code> - <b>Boarding Area</b>. A specific location on a platform, where passengers can board and/or alight vehicles.</li>
 */
@AllArgsConstructor
public enum LocationType implements Parsable<LocationType, Integer> {
    StopOrPlatform(0),
    Station(1),
    EntranceOrExit(2),
    GenericNode(3),
    BoardingArea(4);

    private final int locationType;

    @Override
    public Integer value() {
        return locationType;
    }
}
