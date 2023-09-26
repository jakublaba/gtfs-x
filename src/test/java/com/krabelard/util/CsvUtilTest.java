package com.krabelard.util;

import com.krabelard.model.enums.Parsable;
import com.krabelard.parsers.CsvHeaders;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvUtilTest {
    @Nested
    class ParseCsv {
        @Test
        @SneakyThrows
        void shouldParseCsvCorrectly() {
            var csv = "src/test/resources/csvutil/test.csv";
            var records = CsvUtil.parseCsv(csv);
            var record = records.get(0);
            assertEquals(1, records.size());
            assertEquals("1", record.get("id"));
            assertEquals("John", record.get("name"));
            assertEquals("Smith", record.get("surname"));
            assertEquals("30", record.get("age"));
        }

        @Test
        void shouldThrowWhenFileIsNotPresent() {
            var csv = "something.csv";
            assertThrows(NoSuchFileException.class, () -> CsvUtil.parseCsv(csv));
        }
    }

    @Nested
    class ExtractValues {
        private static final String CSV = "src/test/resources/csvutil/test.csv";
        private static CSVRecord RECORD;

        @BeforeAll
        @SneakyThrows
        static void setupRecord() {
            RECORD = CsvUtil.parseCsv(CSV).get(0);
        }

        @Test
        void shouldNotContainNullsWhenAllHeadersArePresent() {
            String[] headers = {"id", "name", "surname", "age"};
            var values = CsvUtil.extractValues(RECORD, headers);
            assertEquals(headers.length, values.size());
            assertFalse(values.containsValue(null));
            for (var h : headers) {
                assertTrue(values.containsKey(h));
            }
        }

        @Test
        void shouldContainNullsWhenNotAllHeadersArePresent() {
            String[] headers = {"fake_header_1", "another_fake_header"};
            var values = CsvUtil.extractValues(RECORD, headers);
            assertEquals(headers.length, values.size());
            assertTrue(values.containsValue(null));
            for (var h : headers) {
                assertTrue(values.containsKey(h));
            }
        }
    }

    @Nested
    class ParseNullableString {
        @Test
        void shouldReturnOriginalStringWhenStringIsValid() {
            assertEquals("abc", CsvUtil.parseNullableString("abc"));
        }

        @Test
        void shouldReturnNullWhenStringIsNull() {
            assertNull(CsvUtil.parseNullableString(null));
        }

        @Test
        void shouldReturnNullWhenStringIsEmpty() {
            assertNull(CsvUtil.parseNullableString(""));
        }
    }

    @Nested
    class ParseNullableInt {
        @Test
        void shouldReturnIntWhenStringIsParsable() {
            assertEquals(1, CsvUtil.parseNullableInt("1"));
        }

        @Test
        void shouldReturnNullWhenStringIsNull() {
            assertNull(CsvUtil.parseNullableInt(null));
        }

        @Test
        void shouldReturnNullWhenStringIsEmpty() {
            assertNull(CsvUtil.parseNullableInt(""));
        }
    }

    @Nested
    class ParseNullableFloat {
        @Test
        void shouldReturnFloatWhenStringIsParsable() {
            assertEquals(1.2F, CsvUtil.parseNullableFloat("1.2"));
        }

        @Test
        void shouldReturnNullWhenStringIsNull() {
            assertNull(CsvUtil.parseNullableFloat(null));
        }

        @Test
        void shouldReturnNullWhenStringIsEmpty() {
            assertNull(CsvUtil.parseNullableFloat(""));
        }
    }

    @Nested
    class ParseNullableDouble {
        @Test
        void shouldReturnFloatWhenStringIsParsable() {
            assertEquals(1.2, CsvUtil.parseNullableDouble("1.2"));
        }

        @Test
        void shouldReturnNullWhenStringIsNull() {
            assertNull(CsvUtil.parseNullableDouble(null));
        }

        @Test
        void shouldReturnNullWhenStringIsEmpty() {
            assertNull(CsvUtil.parseNullableDouble(""));
        }
    }

    @Nested
    class ParseNullableLocalTime {
        private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("H:mm:ss");

        @Test
        void shouldReturnLocalTimeWhenStringIsParsable() {
            assertEquals(
                    LocalTime.of(1, 2, 3),
                    CsvUtil.parseNullableLocalTime("1:02:03", FORMAT)
            );
        }

        @Test
        void shouldReturnNullWhenStringIsNull() {
            assertNull(CsvUtil.parseNullableLocalTime(null, FORMAT));
        }

        @Test
        void shouldReturnNullWhenStringIsEmpty() {
            assertNull(CsvUtil.parseNullableLocalTime("", FORMAT));
        }
    }

    @Nested
    class ParseNullableLocalDate {
        private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

        @Test
        void shouldReturnLocalDateWhenStringIsParsable() {
            assertEquals(
                    LocalDate.of(2000, Month.JANUARY, 1),
                    CsvUtil.parseNullableLocalDate("20000101", FORMAT)
            );
        }

        @Test
        void shouldReturnNullWhenStringIsNull() {
            assertNull(CsvUtil.parseNullableLocalDate(null, FORMAT));
        }

        @Test
        void shouldReturnNullWhenStringIsEmpty() {
            assertNull(CsvUtil.parseNullableLocalDate("", FORMAT));
        }
    }

    @Nested
    class Parse01Boolean {
        @Test
        void shouldReturnBoolWhenStringIsParsable() {
            assertTrue(CsvUtil.parse01Boolean("1"));
            assertFalse(CsvUtil.parse01Boolean("0"));
        }

        @Test
        void shouldReturnFalseWhenStringIsNull() {
            assertFalse(CsvUtil.parse01Boolean(null));
        }

        @Test
        void shouldReturnFalseWhenStringIsEmpty() {
            assertFalse(CsvUtil.parse01Boolean(""));
        }

        @Test
        void shouldThrowWhenStringIsNotParsable() {
            assertThrows(IllegalArgumentException.class, () -> CsvUtil.parse01Boolean("12"));
        }
    }

    @Nested
    class ParseEnum {
        @RequiredArgsConstructor
        private enum SomeEnum implements Parsable<String> {
            SomeValue("some");

            private final String value;

            @Override
            public String value() {
                return value;
            }
        }

        @RequiredArgsConstructor
        private enum NullEnum implements Parsable<String> {
            Null(null);

            private final String value;

            @Override
            public String value() {
                return value;
            }
        }

        @Test
        void shouldReturnEnumInstanceWhenValueIsParsable() {
            assertEquals(
                    SomeEnum.SomeValue,
                    CsvUtil.parseEnum(SomeEnum.class, "some")
            );
        }

        @Test
        void shouldReturnNullWhenValueIsNullAndEnumDoesNotMapNull() {
            assertNull(CsvUtil.parseEnum(SomeEnum.class, null));
        }

        @Test
        void shouldReturnEnumInstanceWhenValueIsNullAndEnumMapsNull() {
            assertEquals(
                    NullEnum.Null,
                    CsvUtil.parseEnum(NullEnum.class, null)
            );
        }

        @Test
        void shouldThrowWhenValueIsInvalid() {
            assertThrows(IllegalArgumentException.class, () -> CsvUtil.parseEnum(NullEnum.class, "sjdh"));
        }
    }

    @Nested
    class HeadersAsStrings {
        @RequiredArgsConstructor
        @Getter
        private enum Headers implements CsvHeaders {
            Id("id"),
            Name("name"),
            Surname("surname"),
            Age("age");

            private final String value;
        }

        @Test
        void shouldMapCsvHeadersCorrectly() {
            String[] expectedHeaders = {"id", "name", "surname", "age"};
            assertArrayEquals(expectedHeaders, CsvUtil.headersAsStrings(Headers.class));
        }
    }
}