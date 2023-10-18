package com.krabelard.parsers.optional;

import com.krabelard.model.optional.Attribution;
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
public class AttributionParser implements GtfsCsvParser<Attribution> {
    private static final String GTFS_FILE_NAME = "attributions.txt";
    private final String csv;

    private AttributionParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static AttributionParser of(String dir) {
        return new AttributionParser(dir);
    }

    @Override
    public List<Attribution> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Attribution.builder()
                                .id(values.get(Headers.ATTRIBUTION_ID.value))
                                .agencyId(values.get(Headers.AGENCY_ID.value))
                                .routeId(values.get(Headers.ROUTE_ID.value))
                                .tripId(values.get(Headers.TRIP_ID.value))
                                .organizationName(values.get(Headers.ORGANIZATION_NAME.value))
                                .isProducer(CsvUtil.parse01Boolean(values.get(Headers.IS_PRODUCER.value)))
                                .isOperator(CsvUtil.parse01Boolean(values.get(Headers.IS_OPERATOR.value)))
                                .isAuthority(CsvUtil.parse01Boolean(values.get(Headers.IS_AUTHORITY.value)))
                                .url(values.get(Headers.ATTRIBUTION_URL.value))
                                .email(values.get(Headers.ATTRIBUTION_EMAIL.value))
                                .phone(values.get(Headers.ATTRIBUTION_PHONE.value))
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
        ATTRIBUTION_ID("attribution_id"),
        AGENCY_ID("agency_id"),
        ROUTE_ID("route_id"),
        TRIP_ID("trip_id"),
        ORGANIZATION_NAME("organization_name"),
        IS_PRODUCER("is_producer"),
        IS_OPERATOR("is_operator"),
        IS_AUTHORITY("is_authority"),
        ATTRIBUTION_URL("attribution_url"),
        ATTRIBUTION_EMAIL("attribution_email"),
        ATTRIBUTION_PHONE("attribution_phone");

        private final String value;
    }
}
