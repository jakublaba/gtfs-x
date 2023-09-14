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
                                .id(values.get(Headers.PathwayId.value))
                                .fromStopId(values.get(Headers.FromStopId.value))
                                .toStopId(values.get(Headers.ToStopId.value))
                                .mode(CsvUtil.parseEnum(
                                        PathwayMode.class,
                                        Integer.parseInt(values.get(Headers.PathwayMode.value))
                                ))
                                .isBidirectional(CsvUtil.parse01Boolean(values.get(Headers.IsBidirectional.value)))
                                .length(CsvUtil.parseNullableDouble(values.get(Headers.Length.value)))
                                .traversalTime(CsvUtil.parseNullableInt(values.get(Headers.TraversalTime.value)))
                                .stairCount(CsvUtil.parseNullableInt(values.get(Headers.StairCount.value)))
                                .maxSlope(CsvUtil.parseNullableFloat(values.get(Headers.MaxSlope.value)))
                                .minWidth(CsvUtil.parseNullableFloat(values.get(Headers.MinWidth.value)))
                                .signpostedAs(values.get(Headers.SignpostedAs.value))
                                .reverseSignpostedAs(values.get(Headers.ReversedSignpostedAs.value))
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
        PathwayId("pathway_id"),
        FromStopId("from_stop_id"),
        ToStopId("to_stop_id"),
        PathwayMode("pathway_mode"),
        IsBidirectional("is_bidirectional"),
        Length("length"),
        TraversalTime("traversal_time"),
        StairCount("stair_count"),
        MaxSlope("max_slope"),
        MinWidth("min_width"),
        SignpostedAs("signposted_as"),
        ReversedSignpostedAs("reversed_singposted_as");

        private final String value;
    }
}
