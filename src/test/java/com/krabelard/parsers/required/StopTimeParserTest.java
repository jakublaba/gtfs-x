package com.krabelard.parsers.required;

import com.krabelard.model.enums.DropOffType;
import com.krabelard.model.enums.PickupType;
import com.krabelard.model.required.StopTime;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StopTimeParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var stopTimes = StopTimeParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 11;
        var expectedStopTime = StopTime.builder()
                .tripId("AWE1")
                .arrivalTime(LocalTime.of(0, 6, 10))
                .departureTime(LocalTime.of(0, 6, 10))
                .stopId("S1")
                .stopSequence(1)
                .pickupType(PickupType.Pickup)
                .dropOffType(DropOffType.DropOff)
                .build();
        assertEquals(expectedSize, stopTimes.size());
        assertEquals(expectedStopTime, stopTimes.get(0));
    }
}
