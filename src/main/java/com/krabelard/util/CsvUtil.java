package com.krabelard.util;

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

    public static Integer parseNullableInt(String s) {
        if (s == null) {
            return null;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Double parseNullableDouble(String s) {
        if (s == null) {
            return null;
        }
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String[] headersAsStrings(CsvHeaders[] headerValues) {
        return Arrays.stream(headerValues)
                .map(CsvHeaders::getValue)
                .toArray(String[]::new);
    }
}
