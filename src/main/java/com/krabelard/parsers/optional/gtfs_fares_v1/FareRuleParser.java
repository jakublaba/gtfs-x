package com.krabelard.parsers.optional.gtfs_fares_v1;

import com.krabelard.model.optional.gtfs_fares_v1.FareRule;
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
public class FareRuleParser implements GtfsCsvParser<FareRule> {
    private static final String GTFS_FILE_NAME = "fare_rules.txt";
    private final String csv;

    private FareRuleParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static FareRuleParser of(String dir) {
        return new FareRuleParser(dir);
    }

    @Override
    public Collection<FareRule> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return FareRule.builder()
                                .fareId(values.get(Headers.FareId.value))
                                .routeId(values.get(Headers.RouteId.value))
                                .originId(values.get(Headers.OriginId.value))
                                .destinationId(values.get(Headers.DestinationId.value))
                                .containsId(values.get(Headers.ContainsId.value))
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
        FareId("fare_id"),
        RouteId("route_id"),
        OriginId("origin_id"),
        DestinationId("destination_id"),
        ContainsId("contains_id");

        private final String value;
    }
}
