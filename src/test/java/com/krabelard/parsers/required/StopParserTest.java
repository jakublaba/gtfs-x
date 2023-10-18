package com.krabelard.parsers.required;

import com.krabelard.model.required.Stop;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StopParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var stops = StopParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 7;
        var expectedStop = Stop.builder()
                .id("A")
                .name("Victoriaville Transfer Station")
                .latitude(34.514356)
                .longitude(-117.318323)
                .build();
        assertEquals(expectedSize, stops.size());
        assertEquals(expectedStop, stops.get(0));
    }
}
