package com.krabelard.parsers.optional.gtfs_fares_v2;

import com.krabelard.model.optional.gtfs_fares_v2.FareLegRule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FareLegRuleParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var fareLegRules = FareLegRuleParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 4;
        var expectedFareLegRule = FareLegRule.builder()
                .legGroupId("core_local_one_way_trip")
                .networkId("core")
                .fareProductId("core_local_oneway_fare")
                .build();
        assertEquals(expectedSize, fareLegRules.size());
        assertEquals(expectedFareLegRule, fareLegRules.get(0));
    }

    @Test
    void shouldThrowWhenFileNotPresent() {
        var parser = FareLegRuleParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}