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
import java.util.List;

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
    public List<Stop> parse() throws GtfsParsingException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Stop.builder()
                                .id(values.get(Headers.StopId.value))
                                .code(CsvUtil.parseNullableString(values.get(Headers.StopCode.value)))
                                .name(CsvUtil.parseNullableString(values.get(Headers.StopName.value)))
                                .ttsName(CsvUtil.parseNullableString(values.get(Headers.TtsStopName.value)))
                                .description(CsvUtil.parseNullableString(values.get(Headers.StopDescription.value)))
                                .latitude(CsvUtil.parseNullableDouble(values.get(Headers.StopLatitude.value)))
                                .longitude(CsvUtil.parseNullableDouble(values.get(Headers.StopLongitude.value)))
                                .zoneId(CsvUtil.parseNullableString(values.get(Headers.ZoneId.value)))
                                .url(CsvUtil.parseNullableString(values.get(Headers.StopUrl.value)))
                                .locationType(CsvUtil.parseEnum(
                                        LocationType.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.LocationType.value))
                                ))
                                .parentId(CsvUtil.parseNullableString(values.get(Headers.ParentStation.value)))
                                .timezone(CsvUtil.parseNullableString(values.get(Headers.StopTimezone.value)))
                                .wheelchairBoarding(CsvUtil.parseEnum(
                                        WheelchairBoarding.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.WheelchairBoarding.value))
                                ))
                                .levelId(CsvUtil.parseNullableString(values.get(Headers.LevelId.value)))
                                .platformCode(CsvUtil.parseNullableString(values.get(Headers.PlatformCode.value)))
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
        TtsStopName("tts_stop_name"),
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
