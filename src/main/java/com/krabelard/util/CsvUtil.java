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
     * Wrapper around {@link Integer#parseInt(String)}, which provides behavior needed to handle loading optional
     * fields from csv files in GTFS feeds
     *
     * @param s {@link String} to parse
     * @return Parsed value or <code>null</code> if the input was <code>null</code>.
     */
    public static Integer parseNullableInt(String s) {
        if (s == null) {
            return null;
        }
        return Integer.parseInt(s);
    }

    /**
     * Wrapper around {@link Float#parseFloat(String)}, which provides behavior needed to handle loading optional
     * fields from csv files in GTFS feeds
     *
     * @param s {@link String} to parse
     * @return Parsed value or <code>null</code> if the input was <code>null</code>
     */
    public static Float parseNullableFloat(String s) {
        if (s == null) {
            return null;
        }
        return Float.parseFloat(s);
    }

    /**
     * Wrapper around {@link Double#parseDouble(String)}, which provides behavior needed to handle loading optional
     * fields from csv files in GTFS feeds
     *
     * @param s {@link String} to parse
     * @return Parsed value or <code>null</code> if the input was <code>null</code>
     */
    public static Double parseNullableDouble(String s) {
        if (s == null) {
            return null;
        }
        return Double.parseDouble(s);
    }

    /**
     * Method for parsing booleans from 0/1 string representation. Only "0" and "1" arguments are acceptable,
     * other values will throw.
     *
     * @param s {@link String to parse}
     * @return Parsed value
     */
    public static boolean parse01Boolean(String s) {
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
            if (e.value().equals(value)) {
                return e;
            }
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
