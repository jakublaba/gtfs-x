package com.krabelard.parsers.optional.gtfs_fares_v2;

import com.krabelard.model.enums.FareMediaType;
import com.krabelard.model.optional.gtfs_fares_v2.FareMedia;
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
public class FareMediaParser implements GtfsCsvParser<FareMedia> {
    private static final String GTFS_FILE_NAME = "fare_media.txt";
    private final String csv;

    private FareMediaParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static FareMediaParser of(String dir) {
        return new FareMediaParser(dir);
    }

    @Override
    public List<FareMedia> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return FareMedia.builder()
                                .id(values.get(Headers.FareMediaId.value))
                                .name(values.get(Headers.FareMediaName.value))
                                .mediaType(CsvUtil.parseEnum(
                                        FareMediaType.class,
                                        Integer.parseInt(values.get(Headers.FareMediaType.value))
                                ))
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
        FareMediaId("fare_media_id"),
        FareMediaName("fare_media_name"),
        FareMediaType("fare_media_type");

        private final String value;
    }
}
