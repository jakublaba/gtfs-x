package com.krabelard.parsers.required;

import com.krabelard.model.enums.LocationType;
import com.krabelard.model.enums.WheelchairBoarding;
import com.krabelard.model.required.Stop;
import com.krabelard.parsers.CsvHeaders;
import com.krabelard.parsers.GtfsCsvParser;
import com.krabelard.parsers.GtfsParsingException;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;

@Slf4j
public class StopParser implements GtfsCsvParser<Stop> {
    private static final String GTFS_FILE_NAME = "stops.txt";
    private final String csv;

    private StopParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static StopParser of(String dir) {
        return new StopParser(dir);
    }

    @Override
    public Collection<Stop> parse() throws GtfsParsingException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.values());
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Stop.builder()
                                .id(values.get(Headers.StopId.value))
                                .code(values.get(Headers.StopCode.value))
                                .name(values.get(Headers.StopName.value))
                                .description(values.get(Headers.StopDescription.value))
                                .latitude(CsvUtil.parseNullableDouble(values.get(Headers.StopLatitude.value)))
                                .longitude(CsvUtil.parseNullableDouble(values.get(Headers.StopLongitude.value)))
                                .zoneId(values.get(Headers.ZoneId.value))
                                .url(values.get(Headers.StopUrl.value))
                                .locationType(LocationType.from(CsvUtil.parseNullableInt(values.get(Headers.LocationType.value))))
                                .parentId(values.get(Headers.ParentStation.value))
                                .timezone(values.get(Headers.StopTimezone.value))
                                .wheelchairBoarding(WheelchairBoarding.from(CsvUtil.parseNullableInt(values.get(Headers.WheelchairBoarding.value))))
                                .levelId(values.get(Headers.LevelId.value))
                                .platformCode(values.get(Headers.PlatformCode.value))
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
        StopId("stop_id"),
        StopCode("stop_code"),
        StopName("stop_name"),
        StopDescription("stop_desc"),
        StopLatitude("stop_lat"),
        StopLongitude("stop_lon"),
        ZoneId("zone_id"),
        StopUrl("stop_url"),
        LocationType("location_type"),
        ParentStation("parent_station"),
        StopTimezone("stop_timezone"),
        WheelchairBoarding("wheelchair_boarding"),
        LevelId("level_id"),
        PlatformCode("platform_code");

        private final String value;
    }
}
