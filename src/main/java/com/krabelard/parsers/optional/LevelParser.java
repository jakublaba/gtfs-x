package com.krabelard.parsers.optional;

import com.krabelard.model.optional.Level;
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
public class LevelParser implements GtfsCsvParser<Level> {
    private static final String GTFS_FILE_NAME = "levels.txt";
    private final String csv;

    private LevelParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static LevelParser of(String dir) {
        return new LevelParser(dir);
    }

    @Override
    public List<Level> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Level.builder()
                                .id(values.get(Headers.LEVEL_ID.value))
                                .index(Float.parseFloat(values.get(Headers.LEVEL_INDEX.value)))
                                .name(values.get(Headers.LEVEL_NAME.value))
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
        LEVEL_ID("level_id"),
        LEVEL_INDEX("level_index"),
        LEVEL_NAME("level_name");

        private final String value;
    }
}
