package com.krabelard.util;

import com.krabelard.model.enums.Parsable;
import com.krabelard.parsers.CsvHeaders;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CsvUtil {
    private static final CSVFormat GTFS_CSV = CSVFormat.DEFAULT
            .builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .build();

    /**
     * Parses CSV file.
     *
     * @param csv Fully qualified name of the .csv file to parse
     * @return List of parsed records to further process
     * @throws IOException Rethrown from
     */
    public static List<CSVRecord> parseCsv(String csv) throws IOException {
        try (var parser = CSVParser.parse(Paths.get(csv), StandardCharsets.UTF_8, GTFS_CSV)) {
            return parser.getRecords();
        }
    }

    /**
     * Wrapper around parsing csv, which loads non-present fields as nulls
     *
     * @return {@link Map}, where key is a csv column name, and value is corresponding value from a {@link CSVRecord}
     */
    public static Map<String, String> extractValues(CSVRecord record, String[] headers) {
        var result = new HashMap<String, String>();
        for (var h : headers) {
            try {
                var value = record.get(h);
                result.put(h, value);
            } catch (IllegalArgumentException ignored) {
                result.put(h, null);
            }
        }

        return result;
    }

    /**
     * Converts empty strings to nulls, which provides behavior needed to handle loading optional fields from
     * csv files in GTFS feeds
     *
     * @param s {@link String} to parse
     * @return Original string or <code>null</code> if the input was <code>null</code> or empty
     */
    public static String parseNullableString(String s) {
        return (s == null || s.isEmpty()) ? null : s;
    }

    /**
     * Wrapper around {@link Integer#parseInt(String)}, which provides behavior needed to handle loading optional
     * fields from csv files in GTFS feeds
     *
     * @param s {@link String} to parse
     * @return Parsed value or <code>null</code> if the input was <code>null</code> or empty
     */
    public static Integer parseNullableInt(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return Integer.parseInt(s);
    }

    /**
     * Wrapper around {@link Float#parseFloat(String)}, which provides behavior needed to handle loading optional
     * fields from csv files in GTFS feeds
     *
     * @param s {@link String} to parse
     * @return Parsed value or <code>null</code> if the input was <code>null</code> or empty
     */
    public static Float parseNullableFloat(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return Float.parseFloat(s);
    }

    /**
     * Wrapper around {@link Double#parseDouble(String)}, which provides behavior needed to handle loading optional
     * fields from csv files in GTFS feeds
     *
     * @param s {@link String} to parse
     * @return Parsed value or <code>null</code> if the input was <code>null</code> or empty
     */
    public static Double parseNullableDouble(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return Double.parseDouble(s);
    }

    /**
     * Wrapper around {@link LocalTime#parse(CharSequence)}, which provides behavior needed to handle loading
     * optional fields from csv files in GTFS feeds
     *
     * @param s      {@link String} to parse
     * @param format Desired format to parse
     * @return Parsed value or {@code null} if the input was {@code null} or empty
     */
    public static LocalTime parseNullableLocalTime(String s, DateTimeFormatter format) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return LocalTime.parse(s, format);
    }

    /**
     * Wrapper around {@link LocalDate#parse(CharSequence)}, which provides behavior needed to handle loading
     * optional fields from csv files in GTFS feeds
     *
     * @param s      {@link String} to parse
     * @param format Desired format to parse
     * @return Parsed value or <code>null</code> if the input was <code>null</code> or empty
     */
    public static LocalDate parseNullableLocalDate(String s, DateTimeFormatter format) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return LocalDate.parse(s, format);
    }

    /**
     * Method for parsing booleans from 0/1 string representation. According to GTFS specification, non-present fields
     * default to {@code false}. Besides that, only "0" and "1" arguments are acceptable,
     * other values will throw {@link IllegalArgumentException}. Values which cannot be parsed to <code>int</code>
     * will throw {@link NumberFormatException}
     *
     * @param s {@link String to parse}
     * @return Parsed value
     */
    public static boolean parse01Boolean(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        var value = Integer.parseInt(s);
        if (value == 0) {
            return false;
        }
        if (value == 1) {
            return true;
        }
        throw new IllegalArgumentException(s + " doesn't map to a boolean");
    }

    /**
     * Maps enum's underlying value to its instance.W
     *
     * @param enumClass {@link Class} of the enum ({@code SomeEnum.class})
     * @param value     Value of one of the enum options
     * @param <E>       Enum's type
     * @param <V>       Underlying enum's value type
     * @return Instance of specified enum, which corresponds to the supplied value.
     * Throws {@link IllegalArgumentException} if the value does not correspond to any enum option.
     */
    public static <E extends Enum<E> & Parsable<V>, V> E parseEnum(Class<E> enumClass, V value) {
        for (var e : enumClass.getEnumConstants()) {
            if (value == null && e.value() == null) {
                return e;
            }
            if (e.value().equals(value)) {
                return e;
            }
        }
        // Null check after checking enum constants, because one of the enums maps null to a constant
        if (value == null) {
            return null;
        }
        throw new IllegalArgumentException(String.format("%s doesn't map to any %s option", value, enumClass));
    }

    /**
     * Extracts array of values from an enum representing a set of csv headers.
     *
     * @param headerEnumClass {@link Class} of the enum representing the headers
     * @param <E>             Type of the enum
     * @return Array of header options as strings
     */
    public static <E extends Enum<E> & CsvHeaders> String[] headersAsStrings(Class<E> headerEnumClass) {
        return Arrays.stream(headerEnumClass.getEnumConstants())
                .map(CsvHeaders::getValue)
                .toArray(String[]::new);
    }
}
