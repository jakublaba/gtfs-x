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
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    public List<StopTime> parse() throws GtfsParsingException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            var format = DateTimeFormatter.ofPattern("H:mm:ss");
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return StopTime.builder()
                                .tripId(values.get(Headers.TRIP_ID.value))
                                .arrivalTime(CsvUtil.parseNullableLocalTime(values.get(Headers.ARRIVAL_TIME.value), format))
                                .departureTime(CsvUtil.parseNullableLocalTime(values.get(Headers.DEPARTURE_TIME.value), format))
                                .stopId(values.get(Headers.STOP_ID.value))
                                .stopSequence(Integer.parseInt(values.get(Headers.STOP_SEQUENCE.value)))
                                .stopHeadSign(CsvUtil.parseNullableString(values.get(Headers.STOP_HEADSIGN.value)))
                                .pickupType(CsvUtil.parseEnum(
                                        PickupType.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.PICKUP_TYPE.value))
                                ))
                                .dropOffType(CsvUtil.parseEnum(
                                        DropOffType.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.DROP_OFF_TYPE.value))
                                ))
                                .continuousPickup(CsvUtil.parseEnum(
                                        PickupType.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.CONTINUOUS_PICKUP.value))
                                ))
                                .continuousDropOff(CsvUtil.parseEnum(
                                        DropOffType.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.CONTINUOUS_DROP_OFF.value))
                                ))
                                .shapeDistanceTraveled(CsvUtil.parseNullableDouble(values.get(Headers.SHAPE_DISTANCE_TRAVELED.value)))
                                .timePoint(CsvUtil.parseEnum(
                                        TimePoint.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.TIMEPOINT.value))
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
        TRIP_ID("trip_id"),
        ARRIVAL_TIME("arrival_time"),
        DEPARTURE_TIME("departure_time"),
        STOP_ID("stop_id"),
        STOP_SEQUENCE("stop_sequence"),
        STOP_HEADSIGN("stop_headsign"),
        PICKUP_TYPE("pickup_type"),
        DROP_OFF_TYPE("drop_off_type"),
        CONTINUOUS_PICKUP("continuous_pickup"),
        CONTINUOUS_DROP_OFF("continuous_drop_off"),
        SHAPE_DISTANCE_TRAVELED("shape_dist_traveled"),
        TIMEPOINT("timepoint");

        private final String value;
    }
}
