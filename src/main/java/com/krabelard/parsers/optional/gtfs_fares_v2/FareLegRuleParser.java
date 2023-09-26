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
import java.util.List;

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
    public List<FareLegRule> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return FareLegRule.builder()
                                .legGroupId(values.get(Headers.LegGroupId.value))
                                .networkId(values.get(Headers.NetworkId.value))
                                .fromAreaId(values.get(Headers.FromAreaId.value))
                                .toAreaId(values.get(Headers.ToAreaId.value))
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
        LegGroupId("leg_group_id"),
        NetworkId("network_id"),
        FromAreaId("from_area_id"),
        ToAreaId("to_area_id"),
        FromTimeframeGroupId("from_timeframe_group_id"),
        ToTimeframeGroupId("to_timeframe_group_id"),
        FareProductId("fare_product_id");

        private final String value;
    }
}
