package com.krabelard.parsers.optional;

import com.krabelard.model.enums.ServiceType;
import com.krabelard.model.optional.Frequency;
import com.krabelard.parsers.CsvHeaders;
import com.krabelard.parsers.GtfsCsvParser;
import com.krabelard.parsers.GtfsParsingException;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class FrequencyParser implements GtfsCsvParser<Frequency> {
    private static final String GTFS_FILE_NAME = "frequencies.txt";
    private final String csv;

    private FrequencyParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static FrequencyParser of(String dir) {
        return new FrequencyParser(dir);
    }

    @Override
    public List<Frequency> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            var format = DateTimeFormatter.ofPattern("H:mm:ss");
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Frequency.builder()
                                .tripId(values.get(Headers.TripId.value))
                                .startTime(LocalTime.parse(values.get(Headers.StartTime.value), format))
                                .endTime(LocalTime.parse(values.get(Headers.EndTime.value), format))
                                .headwaySecs(Integer.parseInt(values.get(Headers.HeadwaySecs.value)))
                                .exactTimes(CsvUtil.parseEnum(
                                        ServiceType.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.ExactTimes.value))
                                ))
                                .build();
                    })
                    .toList();
        } catch (NoSuchFileException e) {
            throw e;
        } catch (IOException e) {
            throw new GtfsParsingException(csv, e);
        }
    }

    @RequiredArgsConstructor
    @Getter
    private enum Headers implements CsvHeaders {
        TripId("trip_id"),
        StartTime("start_time"),
        EndTime("end_time"),
        HeadwaySecs("headway_secs"),
        ExactTimes("exact_times");

        private final String value;
    }
}
