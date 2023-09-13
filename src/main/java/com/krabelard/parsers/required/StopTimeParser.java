package com.krabelard.parsers.required;

import com.krabelard.model.enums.DropOffType;
import com.krabelard.model.enums.PickupType;
import com.krabelard.model.enums.TimePoint;
import com.krabelard.model.required.StopTime;
import com.krabelard.parsers.CsvHeaders;
import com.krabelard.parsers.GtfsCsvParser;
import com.krabelard.parsers.GtfsParsingException;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Slf4j
public class StopTimeParser implements GtfsCsvParser<StopTime> {
    private static final String GTFS_FILE_NAME = "stop_times.txt";
    private final String csv;

    private StopTimeParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static StopTimeParser of(String dir) {
        return new StopTimeParser(dir);
    }

    @Override
    public Collection<StopTime> parse() throws GtfsParsingException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.values());
            var format = DateTimeFormatter.ofPattern("H:mm:ss");
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return StopTime.builder()
                                .tripId(values.get(Headers.TripId.value))
                                .arrivalTime(LocalTime.parse(values.get(Headers.ArrivalTime.value), format))
                                .departureTime(LocalTime.parse(values.get(Headers.DepartureTime.value), format))
                                .stopId(values.get(Headers.StopId.value))
                                .stopSequence(Integer.parseInt(values.get(Headers.StopSequence.value)))
                                .stopHeadSign(values.get(Headers.StopHeadsign.value))
                                .pickupType(PickupType.from(CsvUtil.parseNullableInt(values.get(Headers.PickupType.value))))
                                .dropOffType(DropOffType.from(CsvUtil.parseNullableInt(values.get(Headers.DropOffType.value))))
                                .shapeDistanceTraveled(CsvUtil.parseNullableDouble(values.get(Headers.ShapeDistanceTraveled.value)))
                                .timePoint(TimePoint.from(CsvUtil.parseNullableInt(values.get(Headers.Timepoint.value))))
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
        TripId("trip_id"),
        ArrivalTime("arrival_time"),
        DepartureTime("departure_time"),
        StopId("stop_id"),
        StopSequence("stop_sequence"),
        StopHeadsign("stop_headsign"),
        PickupType("pickup_type"),
        DropOffType("drop_off_type"),
        ContinuousPickup("continuous_pickup"),
        ContinuousDropOff("continuous_drop_off"),
        ShapeDistanceTraveled("shape_dist_traveled"),
        Timepoint("timepoint");

        private final String value;
    }
}
