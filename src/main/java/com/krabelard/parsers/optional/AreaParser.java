package com.krabelard.parsers.optional;

import com.krabelard.model.optional.Area;
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
public class AreaParser implements GtfsCsvParser<Area> {
    private static final String GTFS_FILE_NAME = "areas.txt";
    private final String csv;

    private AreaParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static AreaParser of(String dir) {
        return new AreaParser(dir);
    }

    @Override
    public Collection<Area> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Area.builder()
                                .id(values.get(Headers.AreaId.value))
                                .name(values.get(Headers.AreaName.value))
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
        AreaName("area_name");

        private final String value;
    }
}
