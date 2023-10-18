package com.krabelard.parsers.optional;

import com.krabelard.model.enums.PathwayMode;
import com.krabelard.model.optional.Pathway;
import com.krabelard.parsers.CsvHeaders;
import com.krabelard.parsers.GtfsCsvParser;
import com.krabelard.parsers.GtfsParsingException;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

@Slf4j
public class PathwayParser implements GtfsCsvParser<Pathway> {
    private static final String GTFS_FILE_NAME = "pathways.txt";
    private final String csv;

    private PathwayParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static PathwayParser of(String dir) {
        return new PathwayParser(dir);
    }

    @Override
    public List<Pathway> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Pathway.builder()
                                .id(values.get(Headers.PATHWAY_ID.value))
                                .fromStopId(values.get(Headers.FROM_STOP_ID.value))
                                .toStopId(values.get(Headers.TO_STOP_ID.value))
                                .mode(CsvUtil.parseEnum(
                                        PathwayMode.class,
                                        Integer.parseInt(values.get(Headers.PATHWAY_MODE.value))
                                ))
                                .isBidirectional(CsvUtil.parse01Boolean(values.get(Headers.IS_BIDIRECTIONAL.value)))
                                .length(CsvUtil.parseNullableDouble(values.get(Headers.LENGTH.value)))
                                .traversalTime(CsvUtil.parseNullableInt(values.get(Headers.TRAVERSAL_TIME.value)))
                                .stairCount(CsvUtil.parseNullableInt(values.get(Headers.STAIR_COUNT.value)))
                                .maxSlope(CsvUtil.parseNullableFloat(values.get(Headers.MAX_SLOPE.value)))
                                .minWidth(CsvUtil.parseNullableFloat(values.get(Headers.MIN_WIDTH.value)))
                                .signpostedAs(values.get(Headers.SIGNPOSTED_AS.value))
                                .reverseSignpostedAs(values.get(Headers.REVERSED_SIGNPOSTED_AS.value))
                                .build();
                    })
                    .toList();
        } catch (NoSuchFileException e) {
            throw e;
        } catch (IOException e) {
            throw new GtfsParsingException(csv, e);
        }
    }

    @RequiredArgsConstructor
    @Getter
    private enum Headers implements CsvHeaders {
        PATHWAY_ID("pathway_id"),
        FROM_STOP_ID("from_stop_id"),
        TO_STOP_ID("to_stop_id"),
        PATHWAY_MODE("pathway_mode"),
        IS_BIDIRECTIONAL("is_bidirectional"),
        LENGTH("length"),
        TRAVERSAL_TIME("traversal_time"),
        STAIR_COUNT("stair_count"),
        MAX_SLOPE("max_slope"),
        MIN_WIDTH("min_width"),
        SIGNPOSTED_AS("signposted_as"),
        REVERSED_SIGNPOSTED_AS("reversed_singposted_as");

        private final String value;
    }
}
