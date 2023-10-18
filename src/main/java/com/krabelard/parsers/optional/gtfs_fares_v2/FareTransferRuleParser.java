package com.krabelard.parsers.optional.gtfs_fares_v2;

import com.krabelard.model.enums.DurationLimitType;
import com.krabelard.model.enums.TransferType;
import com.krabelard.model.optional.gtfs_fares_v2.FareTransferRule;
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
public class FareTransferRuleParser implements GtfsCsvParser<FareTransferRule> {
    private static final String GTFS_FILE_NAME = "fare_transfer_rules.txt";
    private final String csv;

    private FareTransferRuleParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static FareTransferRuleParser of(String dir) {
        return new FareTransferRuleParser(dir);
    }

    @Override
    public List<FareTransferRule> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return FareTransferRule.builder()
                                .fromLegGroupId(values.get(Headers.FROM_LEG_GROUP_ID.value))
                                .toLegGroupId(values.get(Headers.TO_LEG_GROUP_ID.value))
                                .transferCount(CsvUtil.parseNullableInt(values.get(Headers.TRANSFER_COUNT.value)))
                                .durationLimit(CsvUtil.parseNullableInt(values.get(Headers.DURATION_LIMIT.value)))
                                .durationLimitType(CsvUtil.parseEnum(
                                        DurationLimitType.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.DURATION_LIMIT.value))
                                ))
                                .fareProductId(values.get(Headers.FARE_PRODUCT_ID.value))
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
        FROM_LEG_GROUP_ID("from_leg_group_id"),
        TO_LEG_GROUP_ID("to_leg_group_id"),
        TRANSFER_COUNT("transfer_count"),
        DURATION_LIMIT("duration_limit"),
        DURATION_LIMIT_TYPE("duration_limit_type"),
        FARE_TRANSFER_TYPE("fare_transfer_type"),
        FARE_PRODUCT_ID("fare_product_id");

        private final String value;
    }
}
