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
                                .serviceId(values.get(Headers.ServiceId.value))
                                .monday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.Monday.value))
                                ))
                                .tuesday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.Tuesday.value))
                                ))
                                .wednesday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.Wednesday.value))
                                ))
                                .thursday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.Thursday.value))
                                ))
                                .friday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.Friday.value))
                                ))
                                .saturday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.Saturday.value))
                                ))
                                .sunday(CsvUtil.parseEnum(
                                        ServiceAvailability.class,
                                        Integer.parseInt(values.get(Headers.Sunday.value))
                                ))
                                .startDate(LocalDate.parse(values.get(Headers.StartDate.value), formatter))
                                .endDate(LocalDate.parse(values.get(Headers.EndDate.value), formatter))
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
        ServiceId("service_id"),
        Monday("monday"),
        Tuesday("tuesday"),
        Wednesday("wednesday"),
        Thursday("thursday"),
        Friday("friday"),
        Saturday("saturday"),
        Sunday("sunday"),
        StartDate("start_date"),
        EndDate("end_date");

        private final String value;
    }
}
