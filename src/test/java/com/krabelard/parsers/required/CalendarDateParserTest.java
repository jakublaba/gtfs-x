package com.krabelard.parsers.required;

import com.krabelard.model.enums.ExceptionType;
import com.krabelard.model.required.CalendarDate;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarDateParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var calendarDates = CalendarDateParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 1;
        var expectedCalendarDate = CalendarDate.builder()
                .serviceId("weekend_service")
                .date(LocalDate.of(2022, Month.JUNE, 23))
                .exceptionType(ExceptionType.REMOVED)
                .build();
        assertEquals(expectedSize, calendarDates.size());
        assertEquals(expectedCalendarDate, calendarDates.get(0));
    }
}
