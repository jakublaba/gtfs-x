package com.krabelard.parsers.required;

import com.krabelard.model.enums.Direction;
import com.krabelard.model.required.Trip;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var trips = TripParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 3;
        var expectedTrip = Trip.builder()
                .routeId("303-20670")
                .serviceId("weekend_service")
                .id("60270564")
                .headSign("MAX ORANGE SADDLETOWNE")
                .direction(Direction.OUTBOUND)
                .shapeId("3030026")
                .build();
        assertEquals(expectedSize, trips.size());
        assertEquals(expectedTrip, trips.get(0));
    }
}
