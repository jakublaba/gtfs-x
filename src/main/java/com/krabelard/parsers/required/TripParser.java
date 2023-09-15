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
                                .routeId(values.get(Headers.RouteId.value))
                                .serviceId(values.get(Headers.ServiceId.value))
                                .id(values.get(Headers.TripId.value))
                                .headSign(CsvUtil.parseNullableString(values.get(Headers.TripHeadsign.value)))
                                .shortName(CsvUtil.parseNullableString(values.get(Headers.TripShortName.value)))
                                .direction(CsvUtil.parseEnum(
                                        Direction.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.DirectionId.value))
                                ))
                                .blockId(CsvUtil.parseNullableString(values.get(Headers.BlockId.value)))
                                .shapeId(CsvUtil.parseNullableString(values.get(Headers.ShapeId.value)))
                                .wheelchairAccessible(CsvUtil.parseEnum(
                                        WheelchairAccessibility.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.WheelchairAccessible.value))
                                ))
                                .bikesAllowed(CsvUtil.parseEnum(
                                        BikesAllowed.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.BikesAllowed.value))
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
        RouteId("route_id"),
        ServiceId("service_id"),
        TripId("trip_id"),
        TripHeadsign("trip_headsign"),
        TripShortName("trip_short_name"),
        DirectionId("direction_id"),
        BlockId("block_id"),
        ShapeId("shape_id"),
        WheelchairAccessible("wheelchair_accessible"),
        BikesAllowed("bikes_allowed");

        private final String value;
    }
}
