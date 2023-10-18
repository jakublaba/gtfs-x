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
                                .id(values.get(Headers.STOP_ID.value))
                                .code(CsvUtil.parseNullableString(values.get(Headers.STOP_CODE.value)))
                                .name(CsvUtil.parseNullableString(values.get(Headers.STOP_NAME.value)))
                                .ttsName(CsvUtil.parseNullableString(values.get(Headers.TTS_STOP_NAME.value)))
                                .description(CsvUtil.parseNullableString(values.get(Headers.STOP_DESCRIPTION.value)))
                                .latitude(CsvUtil.parseNullableDouble(values.get(Headers.STOP_LATITUDE.value)))
                                .longitude(CsvUtil.parseNullableDouble(values.get(Headers.STOP_LONGITUDE.value)))
                                .zoneId(CsvUtil.parseNullableString(values.get(Headers.ZONE_ID.value)))
                                .url(CsvUtil.parseNullableString(values.get(Headers.STOP_URL.value)))
                                .locationType(CsvUtil.parseEnum(
                                        LocationType.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.LOCATION_TYPE.value))
                                ))
                                .parentId(CsvUtil.parseNullableString(values.get(Headers.PARENT_STATION.value)))
                                .timezone(CsvUtil.parseNullableString(values.get(Headers.STOP_TIMEZONE.value)))
                                .wheelchairBoarding(CsvUtil.parseEnum(
                                        WheelchairBoarding.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.WHEELCHAIR_BOARDING.value))
                                ))
                                .levelId(CsvUtil.parseNullableString(values.get(Headers.LEVEL_ID.value)))
                                .platformCode(CsvUtil.parseNullableString(values.get(Headers.PLATFORM_CODE.value)))
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
        STOP_ID("stop_id"),
        STOP_CODE("stop_code"),
        STOP_NAME("stop_name"),
        TTS_STOP_NAME("tts_stop_name"),
        STOP_DESCRIPTION("stop_desc"),
        STOP_LATITUDE("stop_lat"),
        STOP_LONGITUDE("stop_lon"),
        ZONE_ID("zone_id"),
        STOP_URL("stop_url"),
        LOCATION_TYPE("location_type"),
        PARENT_STATION("parent_station"),
        STOP_TIMEZONE("stop_timezone"),
        WHEELCHAIR_BOARDING("wheelchair_boarding"),
        LEVEL_ID("level_id"),
        PLATFORM_CODE("platform_code");

        private final String value;
    }
}
