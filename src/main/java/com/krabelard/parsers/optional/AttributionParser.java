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
                                .id(values.get(Headers.AttributionId.value))
                                .agencyId(values.get(Headers.AgencyId.value))
                                .routeId(values.get(Headers.RouteId.value))
                                .tripId(values.get(Headers.TripId.value))
                                .organizationName(values.get(Headers.OrganizationName.value))
                                .isProducer(CsvUtil.parse01Boolean(values.get(Headers.IsProducer.value)))
                                .isOperator(CsvUtil.parse01Boolean(values.get(Headers.IsOperator.value)))
                                .isAuthority(CsvUtil.parse01Boolean(values.get(Headers.IsAuthority.value)))
                                .url(values.get(Headers.AttributionUrl.value))
                                .email(values.get(Headers.AttributionEmail.value))
                                .phone(values.get(Headers.AttributionPhone.value))
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
        AttributionId("attribution_id"),
        AgencyId("agency_id"),
        RouteId("route_id"),
        TripId("trip_id"),
        OrganizationName("organization_name"),
        IsProducer("is_producer"),
        IsOperator("is_operator"),
        IsAuthority("is_authority"),
        AttributionUrl("attribution_url"),
        AttributionEmail("attribution_email"),
        AttributionPhone("attribution_phone");

        private final String value;
    }
}
