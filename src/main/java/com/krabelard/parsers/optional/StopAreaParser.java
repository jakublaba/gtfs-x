package com.krabelard.parsers.optional;

import com.krabelard.model.optional.StopArea;
import com.krabelard.parsers.CsvHeaders;
import com.krabelard.parsers.GtfsCsvParser;
import com.krabelard.parsers.GtfsParsingException;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Collection;

@Slf4j
public class StopAreaParser implements GtfsCsvParser<StopArea> {
    private static final String GTFS_FILE_NAME = "stop_areas.txt";
    private final String csv;

    private StopAreaParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static StopAreaParser of(String dir) {
        return new StopAreaParser(dir);
    }

    @Override
    public Collection<StopArea> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return StopArea.builder()
                                .areaId(values.get(Headers.AreaId.value))
                                .stopId(values.get(Headers.StopId.value))
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
        AreaId("area_id"),
        StopId("stop_id");

        private final String value;
    }
}
