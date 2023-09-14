package com.krabelard.parsers.required;


import com.krabelard.model.required.Agency;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AgencyParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var agencies = AgencyParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 1;
        var expectedAgency = Agency.builder()
                .id("FunBus")
                .name("The Fun Bus")
                .url("https://www.thefunbus.org")
                .timezone("America/Los_Angeles")
                .phone("(310) 555-0222")
                .language("en")
                .build();
        assertEquals(expectedSize, agencies.size());
        assertEquals(expectedAgency, agencies.get(0));
    }
}