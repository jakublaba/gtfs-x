package com.krabelard.parsers.optional.gtfs_fares_v2;

import com.krabelard.model.optional.gtfs_fares_v2.FareLegRule;
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
public class FareLegRuleParser implements GtfsCsvParser<FareLegRule> {
    private static final String GTFS_FILE_NAME = "fare_leg_rules.txt";
    private final String csv;

    private FareLegRuleParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static FareLegRuleParser of(String dir) {
        return new FareLegRuleParser(dir);
    }

    @Override
    public Collection<FareLegRule> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return FareLegRule.builder()
                                .legGroupId(values.get(Headers.LEG_GROUP_ID.value))
                                .networkId(values.get(Headers.NETWORK_ID.value))
                                .fromAreaId(values.get(Headers.FROM_AREA_ID.value))
                                .toAreaId(values.get(Headers.TO_AREA_ID.value))
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
        LEG_GROUP_ID("leg_group_id"),
        NETWORK_ID("network_id"),
        FROM_AREA_ID("from_area_id"),
        TO_AREA_ID("to_area_id"),
        FROM_TIMEFRAME_GROUP_ID("from_timeframe_group_id"),
        TO_TIMEFRAME_GROUP_ID("to_timeframe_group_id"),
        FARE_PRODUCT_ID("fare_product_id");

        private final String value;
    }
}
