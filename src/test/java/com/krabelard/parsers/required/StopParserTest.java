package com.krabelard.parsers.required;

import com.krabelard.model.enums.LocationType;
import com.krabelard.model.required.Stop;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StopParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var stops = StopParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 16;
        var expectedStop = Stop.builder()
                .id("F12")
                .name("5 Av/53 St")
                .latitude(40.760167)
                .longitude(-73.975224)
                .locationType(LocationType.Station)
                .build();
        assertEquals(expectedSize, stops.size());
        assertEquals(expectedStop, stops.get(0));
    }
}
