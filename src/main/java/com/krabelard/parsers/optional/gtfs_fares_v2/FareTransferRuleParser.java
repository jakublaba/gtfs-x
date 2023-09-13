package com.krabelard.parsers.optional.gtfs_fares_v2;

import com.krabelard.model.enums.DurationLimitType;
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
import java.util.Collection;

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
    public Collection<FareTransferRule> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.values());
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return FareTransferRule.builder()
                                .fromLegGroupId(values.get(Headers.FromLegGroupId.value))
                                .toLegGroupId(values.get(Headers.ToLegGroupId.value))
                                .transferCount(CsvUtil.parseNullableInt(values.get(Headers.TransferCount.value)))
                                .durationLimit(CsvUtil.parseNullableInt(values.get(Headers.DurationLimit.value)))
                                .durationLimitType(CsvUtil.parseEnum(
                                        DurationLimitType.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.DurationLimit.value))
                                ))
                                .fareProductId(values.get(Headers.FareProductId.value))
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
        FromLegGroupId("from_leg_group_id"),
        ToLegGroupId("to_leg_group_id"),
        TransferCount("transfer_count"),
        DurationLimit("duration_limit"),
        DurationLimitType("duration_limit_type"),
        FareTransferType("fare_transfer_type"),
        FareProductId("fare_product_id");

        private final String value;
    }
}
