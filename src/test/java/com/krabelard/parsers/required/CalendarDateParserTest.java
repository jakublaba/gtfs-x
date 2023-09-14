package com.krabelard.parsers.required;

import com.krabelard.model.enums.ExceptionType;
import com.krabelard.model.required.CalendarDate;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarDateParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var calendarDates = CalendarDateParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 4;
        var expectedCalendarDate = CalendarDate.builder()
                .serviceId("WD")
                .date(LocalDate.of(2006, Month.JULY, 3))
                .exceptionType(ExceptionType.Removed)
                .build();
        assertEquals(expectedSize, calendarDates.size());
        assertEquals(expectedCalendarDate, calendarDates.get(0));
    }
}
