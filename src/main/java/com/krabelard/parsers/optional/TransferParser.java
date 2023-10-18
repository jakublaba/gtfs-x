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
                                .fromStopId(CsvUtil.parseNullableString(values.get(Headers.FROM_STOP_ID.value)))
                                .toStopId(CsvUtil.parseNullableString(values.get(Headers.TO_STOP_ID.value)))
                                .fromRouteId(CsvUtil.parseNullableString(values.get(Headers.FROM_ROUTE_ID.value)))
                                .toRouteId(CsvUtil.parseNullableString(values.get(Headers.TO_ROUTE_ID.value)))
                                .fromTripId(CsvUtil.parseNullableString(values.get(Headers.FROM_TRIP_ID.value)))
                                .toTripId(CsvUtil.parseNullableString(values.get(Headers.TO_TRIP_ID.value)))
                                .transferType(CsvUtil.parseEnum(
                                        TransferType.class,
                                        Integer.parseInt(values.get(Headers.TRANSFER_TYPE.value))
                                ))
                                .minTransferTime(CsvUtil.parseNullableInt(values.get(Headers.MIN_TRANSFER_TIME.value)))
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
        FROM_STOP_ID("from_stop_id"),
        TO_STOP_ID("to_stop_id"),
        FROM_ROUTE_ID("from_route_id"),
        TO_ROUTE_ID("to_route_id"),
        FROM_TRIP_ID("from_trip_id"),
        TO_TRIP_ID("to_trip_id"),
        TRANSFER_TYPE("transfer_type"),
        MIN_TRANSFER_TIME("min_transfer_time");

        private final String value;
    }
}
