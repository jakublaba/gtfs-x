package com.krabelard.parsers.optional.gtfs_fares_v2;

import com.krabelard.model.enums.FareMediaType;
import com.krabelard.model.optional.gtfs_fares_v2.FareMedia;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FareMediaParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var fareMedia = FareMediaParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 3;
        var expectedFareMedia = FareMedia.builder()
                .id("clipper")
                .name("Clipper")
                .mediaType(FareMediaType.PHYSICAL_TRANSIT_CARD)
                .build();
        assertEquals(expectedSize, fareMedia.size());
        assertEquals(expectedFareMedia, fareMedia.get(0));
    }

    @Test
    void shouldThrowWhenFileNotPresent() {
        var parser = FareMediaParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}