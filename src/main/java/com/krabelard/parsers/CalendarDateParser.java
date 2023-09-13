package com.krabelard.parsers;

import com.krabelard.model.enums.ExceptionType;
import com.krabelard.model.required.CalendarDate;
import com.krabelard.util.CsvUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;

@Slf4j
public class CalendarDateParser implements GtfsCsvParser<CalendarDate> {
    private static final String GTFS_FILE_NAME = "calendar_dates.txt";
    private final String csv;

    private CalendarDateParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static CalendarDateParser of(String dir) {
        return new CalendarDateParser(dir);
    }

    @Override
    public Collection<CalendarDate> parse() throws GtfsParsingException {
        try {
            log.info("Parsing {}", csv);
            var headers = Arrays.stream(Headers.values())
                    .map(h -> h.value)
                    .toArray(String[]::new);
            var format = DateTimeFormatter.ofPattern("yyyyMMdd");
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return CalendarDate.builder()
                                .serviceId(values.get(Headers.ServiceId.value))
                                .date(LocalDate.parse(values.get(Headers.Date.value), format))
                                .exceptionType(ExceptionType.from(values.get(Headers.ExceptionType.value)))
                                .build();
                    })
                    .toList();
        } catch (IOException e) {
            throw new GtfsParsingException(csv, e);
        }
    }

    @RequiredArgsConstructor
    private enum Headers {
        ServiceId("service_id"),
        Date("date"),
        ExceptionType("exception_type");

        private final String value;
    }
}
