package com.krabelard.parsers.required;

import com.krabelard.model.enums.ServiceAvailability;
import com.krabelard.model.required.Calendar;
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
public class CalendarParser implements GtfsCsvParser<Calendar> {
    private static final String GTFS_FILE_NAME = "calendar.txt";
    private final String csv;

    private CalendarParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static CalendarParser of(String dir) {
        return new CalendarParser(dir);
    }

    @Override
    public List<Calendar> parse() throws GtfsParsingException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            var formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Calendar.builder()
                                .serviceId(values.get(Headers.SERVICE_ID.value))
                                .monday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.MONDAY.value))
                                ))
                                .tuesday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.TUESDAY.value))
                                ))
                                .wednesday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.WEDNESDAY.value))
                                ))
                                .thursday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.THURSDAY.value))
                                ))
                                .friday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.FRIDAY.value))
                                ))
                                .saturday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.SATURDAY.value))
                                ))
                                .sunday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.SUNDAY.value))
                                ))
                                .startDate(LocalDate.parse(values.get(Headers.START_DATE.value), formatter))
                                .endDate(LocalDate.parse(values.get(Headers.END_DATE.value), formatter))
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
        MONDAY("monday"),
        TUESDAY("tuesday"),
        WEDNESDAY("wednesday"),
        THURSDAY("thursday"),
        FRIDAY("friday"),
        SATURDAY("saturday"),
        SUNDAY("sunday"),
        START_DATE("start_date"),
        END_DATE("end_date");

        private final String value;
    }
}
