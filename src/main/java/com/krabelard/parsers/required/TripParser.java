package com.krabelard.parsers.required;

import com.krabelard.model.enums.BikesAllowed;
import com.krabelard.model.enums.Direction;
import com.krabelard.model.enums.WheelchairAccessibility;
import com.krabelard.model.required.Trip;
import com.krabelard.parsers.CsvHeaders;
import com.krabelard.parsers.GtfsCsvParser;
import com.krabelard.parsers.GtfsParsingException;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class TripParser implements GtfsCsvParser<Trip> {
    private static final String GTFS_FILE_NAME = "trips.txt";
    private final String csv;

    private TripParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static TripParser of(String dir) {
        return new TripParser(dir);
    }

    @Override
    public List<Trip> parse() throws GtfsParsingException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Trip.builder()
                                .routeId(values.get(Headers.ROUTE_ID.value))
                                .serviceId(values.get(Headers.SERVICE_ID.value))
                                .id(values.get(Headers.TRIP_ID.value))
                                .headSign(CsvUtil.parseNullableString(values.get(Headers.TRIP_HEADSIGN.value)))
                                .shortName(CsvUtil.parseNullableString(values.get(Headers.TRIP_SHORT_NAME.value)))
                                .direction(CsvUtil.parseEnum(
                                        Direction.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.DIRECTION_ID.value))
                                ))
                                .blockId(CsvUtil.parseNullableString(values.get(Headers.BLOCK_ID.value)))
                                .shapeId(CsvUtil.parseNullableString(values.get(Headers.SHAPE_ID.value)))
                                .wheelchairAccessible(CsvUtil.parseEnum(
                                        WheelchairAccessibility.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.WHEELCHAIR_ACCESSIBLE.value))
                                ))
                                .bikesAllowed(CsvUtil.parseEnum(
                                        BikesAllowed.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.BIKES_ALLOWED.value))
                                ))
                                .build();
                    })
                    .toList();
        } catch (IOException e) {
            throw new GtfsParsingException(csv, e);
        }
    }

    @RequiredArgsConstructor
    @Getter
    private enum Headers implements CsvHeaders {
        ROUTE_ID("route_id"),
        SERVICE_ID("service_id"),
        TRIP_ID("trip_id"),
        TRIP_HEADSIGN("trip_headsign"),
        TRIP_SHORT_NAME("trip_short_name"),
        DIRECTION_ID("direction_id"),
        BLOCK_ID("block_id"),
        SHAPE_ID("shape_id"),
        WHEELCHAIR_ACCESSIBLE("wheelchair_accessible"),
        BIKES_ALLOWED("bikes_allowed");

        private final String value;
    }
}
