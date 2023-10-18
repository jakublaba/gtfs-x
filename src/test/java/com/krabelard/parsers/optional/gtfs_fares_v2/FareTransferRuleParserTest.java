package com.krabelard.parsers.optional.gtfs_fares_v2;

import com.krabelard.model.enums.DurationLimitType;
import com.krabelard.model.enums.TransferType;
import com.krabelard.model.optional.gtfs_fares_v2.FareTransferRule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FareTransferRuleParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var fareTransferRules = FareTransferRuleParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 1;
        var expectedFareTransferRule = FareTransferRule.builder()
                .fromLegGroupId("core_local_one_way_trip")
                .toLegGroupId("core_local_one_way_trip")
                .durationLimit(5400)
                .durationLimitType(DurationLimitType.BETWEEN_DEPARTURE_AND_DEPARTURE)
                .transferType(TransferType.RECOMMENDED)
                .transferCount(-1)
                .build();
        assertEquals(expectedSize, fareTransferRules.size());
        assertEquals(expectedFareTransferRule, fareTransferRules.get(0));
    }

    @Test
    void shouldThrowWhenFileNotPresent() {
        var parser = FareTransferRuleParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}