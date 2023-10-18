package com.krabelard.parsers.required;

import com.krabelard.model.required.Agency;
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
public class AgencyParser implements GtfsCsvParser<Agency> {
    private static final String GTFS_FILE_NAME = "agency.txt";
    private final String csv;

    private AgencyParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static AgencyParser of(String dir) {
        return new AgencyParser(dir);
    }

    @Override
    public List<Agency> parse() throws GtfsParsingException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Agency.builder()
                                .id(CsvUtil.parseNullableString(values.get(Headers.AGENCY_ID.value)))
                                .name(values.get(Headers.AGENCY_NAME.value))
                                .url(values.get(Headers.AGENCY_URL.value))
                                .timezone(values.get(Headers.AGENCY_TIMEZONE.value))
                                .language(CsvUtil.parseNullableString(values.get(Headers.AGENCY_LANG.value)))
                                .phone(CsvUtil.parseNullableString(values.get(Headers.AGENCY_PHONE.value)))
                                .fareUrl(CsvUtil.parseNullableString(values.get(Headers.AGENCY_FARE_URL.value)))
                                .email(CsvUtil.parseNullableString(values.get(Headers.AGENCY_EMAIL.value)))
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
        AGENCY_ID("agency_id"),
        AGENCY_NAME("agency_name"),
        AGENCY_URL("agency_url"),
        AGENCY_TIMEZONE("agency_timezone"),
        AGENCY_LANG("agency_lang"),
        AGENCY_PHONE("agency_phone"),
        AGENCY_FARE_URL("agency_fare_url"),
        AGENCY_EMAIL("agency_email");

        private final String value;
    }
}
