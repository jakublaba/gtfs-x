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
                                .id(CsvUtil.parseNullableString(values.get(Headers.AgencyId.value)))
                                .name(values.get(Headers.AgencyName.value))
                                .url(values.get(Headers.AgencyUrl.value))
                                .timezone(values.get(Headers.AgencyTimezone.value))
                                .language(CsvUtil.parseNullableString(values.get(Headers.AgencyLang.value)))
                                .phone(CsvUtil.parseNullableString(values.get(Headers.AgencyPhone.value)))
                                .fareUrl(CsvUtil.parseNullableString(values.get(Headers.AgencyFareUrl.value)))
                                .email(CsvUtil.parseNullableString(values.get(Headers.AgencyEmail.value)))
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
        AgencyId("agency_id"),
        AgencyName("agency_name"),
        AgencyUrl("agency_url"),
        AgencyTimezone("agency_timezone"),
        AgencyLang("agency_lang"),
        AgencyPhone("agency_phone"),
        AgencyFareUrl("agency_fare_url"),
        AgencyEmail("agency_email");

        private final String value;
    }
}
