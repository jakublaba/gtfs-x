package com.krabelard.parsers.required;

import com.krabelard.model.required.Trip;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var trips = TripParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 2;
        var expectedTrip = Trip.builder()
                .routeId("A")
                .serviceId("WE")
                .id("AWE1")
                .headSign("Downtown")
                .blockId("1")
                .build();
        assertEquals(trips.size(), expectedSize);
        assertEquals(trips.get(0), expectedTrip);
    }
}
