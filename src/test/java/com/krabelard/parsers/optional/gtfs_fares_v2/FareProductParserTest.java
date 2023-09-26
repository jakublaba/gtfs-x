package com.krabelard.parsers.optional.gtfs_fares_v2;

import com.krabelard.model.optional.gtfs_fares_v2.FareProduct;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FareProductParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var fareProducts = FareProductParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 4;
        var expectedFareProduct = FareProduct.builder()
                .id("core_local_oneway_fare")
                .name("One Way Full Fare")
                .amount(2.00)
                .currency("USD")
                .build();
        assertEquals(expectedSize, fareProducts.size());
        assertEquals(expectedFareProduct, fareProducts.get(0));
    }

    @Test
    void shouldThrowWhenFileNotPresent() {
        var parser = FareProductParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}