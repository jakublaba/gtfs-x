package com.krabelard.parsers.required;

import com.krabelard.model.enums.ExceptionType;
import com.krabelard.model.required.CalendarDate;
import com.krabelard.parsers.CsvHeaders;
import com.krabelard.parsers.GtfsCsvParser;
import com.krabelard.parsers.GtfsParsingException;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    public List<CalendarDate> parse() throws GtfsParsingException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            var format = DateTimeFormatter.ofPattern("yyyyMMdd");
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return CalendarDate.builder()
                                .serviceId(values.get(Headers.SERVICE_ID.value))
                                .date(LocalDate.parse(values.get(Headers.DATE.value), format))
                                .exceptionType(CsvUtil.parseEnum(
                                        ExceptionType.class,
                                        Integer.parseInt(values.get(Headers.EXCEPTION_TYPE.value))
                                ))
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
        SERVICE_ID("service_id"),
        DATE("date"),
        EXCEPTION_TYPE("exception_type");

        private final String value;
    }
}
