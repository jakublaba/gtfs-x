package com.krabelard.parsers;

import com.krabelard.model.enums.ServiceAvailability;
import com.krabelard.model.required.Calendar;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;

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
    public Collection<Calendar> parse() throws GtfsParsingException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.values());
            var formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Calendar.builder()
                                .serviceId(values.get(Headers.ServiceId.value))
                                .monday(ServiceAvailability.from(values.get(Headers.Monday.value)))
                                .tuesday(ServiceAvailability.from(values.get(Headers.Tuesday.value)))
                                .wednesday(ServiceAvailability.from(values.get(Headers.Wednesday.value)))
                                .thursday(ServiceAvailability.from(values.get(Headers.Thursday.value)))
                                .friday(ServiceAvailability.from(values.get(Headers.Friday.value)))
                                .saturday(ServiceAvailability.from(values.get(Headers.Saturday.value)))
                                .sunday(ServiceAvailability.from(values.get(Headers.Sunday.value)))
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
