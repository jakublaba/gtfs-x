package com.krabelard.parsers.optional;

import com.krabelard.model.optional.Attribution;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AttributionParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var attributions = AttributionParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 2;
        var expectedAttribution = Attribution.builder()
                .id("attribution001")
                .isProducer(true)
                .isOperator(false)
                .isAuthority(false)
                .organizationName("Transit Feed Solutions USA")
                .agencyId("agency001")
                .build();
        assertEquals(expectedSize, attributions.size());
        assertEquals(expectedAttribution, attributions.get(0));
    }

    @Test
    void shouldThrowWhenFileNotPresent() {
        var parser = AttributionParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}
