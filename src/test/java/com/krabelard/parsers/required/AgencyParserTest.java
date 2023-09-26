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
                .id("CT")
                .name("Calgary Transit")
                .url("http://www.calgarytransit.com")
                .timezone("America/Edmonton")
                .phone("403-262-1000")
                .build();
        assertEquals(expectedSize, agencies.size());
        assertEquals(expectedAgency, agencies.get(0));
    }
}