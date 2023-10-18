package com.krabelard.parsers.required;

import com.krabelard.model.enums.ServiceAvailability;
import com.krabelard.model.required.Calendar;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var calendars = CalendarParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 1;
        var expectedCalendar = Calendar.builder()
                .serviceId("weekend_service")
                .monday(ServiceAvailability.NOT_AVAILABLE)
                .tuesday(ServiceAvailability.NOT_AVAILABLE)
                .wednesday(ServiceAvailability.NOT_AVAILABLE)
                .thursday(ServiceAvailability.NOT_AVAILABLE)
                .friday(ServiceAvailability.NOT_AVAILABLE)
                .saturday(ServiceAvailability.AVAILABLE)
                .sunday(ServiceAvailability.AVAILABLE)
                .startDate(LocalDate.of(2022, Month.JUNE, 23))
                .endDate(LocalDate.of(2022, Month.SEPTEMBER, 3))
                .build();
        assertEquals(expectedSize, calendars.size());
        assertEquals(expectedCalendar, calendars.get(0));
    }
}
