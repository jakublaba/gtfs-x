package com.krabelard.parsers.optional;

import com.krabelard.model.enums.TransferType;
import com.krabelard.model.optional.Transfer;
import com.krabelard.parsers.CsvHeaders;
import com.krabelard.parsers.GtfsCsvParser;
import com.krabelard.parsers.GtfsParsingException;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

@Slf4j
public class TransferParser implements GtfsCsvParser<Transfer> {
    private static final String GTFS_FILE_NAME = "transfers.txt";
    private final String csv;

    private TransferParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static TransferParser of(String dir) {
        return new TransferParser(dir);
    }

    @Override
    public List<Transfer> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Transfer.builder()
                                .fromStopId(CsvUtil.parseNullableString(values.get(Headers.FromStopId.value)))
                                .toStopId(CsvUtil.parseNullableString(values.get(Headers.ToStopId.value)))
                                .fromRouteId(CsvUtil.parseNullableString(values.get(Headers.FromRouteId.value)))
                                .toRouteId(CsvUtil.parseNullableString(values.get(Headers.ToRouteId.value)))
                                .fromTripId(CsvUtil.parseNullableString(values.get(Headers.FromTripId.value)))
                                .toTripId(CsvUtil.parseNullableString(values.get(Headers.ToTripId.value)))
                                .transferType(CsvUtil.parseEnum(
                                        TransferType.class,
                                        Integer.parseInt(values.get(Headers.TransferType.value))
                                ))
                                .minTransferTime(CsvUtil.parseNullableInt(values.get(Headers.MinTransferTime.value)))
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
        FromStopId("from_stop_id"),
        ToStopId("to_stop_id"),
        FromRouteId("from_route_id"),
        ToRouteId("to_route_id"),
        FromTripId("from_trip_id"),
        ToTripId("to_trip_id"),
        TransferType("transfer_type"),
        MinTransferTime("min_transfer_time");

        private final String value;
    }
}
