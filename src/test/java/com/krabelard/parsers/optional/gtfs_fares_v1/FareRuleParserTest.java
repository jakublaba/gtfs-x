package com.krabelard.parsers.optional.gtfs_fares_v1;

import com.krabelard.model.optional.gtfs_fares_v1.FareRule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FareRuleParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var fareRules = FareRuleParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 2;
        var expectedFareRule = FareRule.builder()
                .fareId("presto_fare")
                .routeId("line1")
                .originId("ttc_subway_stations")
                .destinationId("ttc_subway_stations")
                .build();
        assertEquals(expectedSize, fareRules.size());
        assertEquals(expectedFareRule, fareRules.get(0));
    }

    @Test
    void shouldThrowWhenFileNotPresent() {
        var parser = FareRuleParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}