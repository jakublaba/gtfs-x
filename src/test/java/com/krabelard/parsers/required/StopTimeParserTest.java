package com.krabelard.parsers.required;

import com.krabelard.model.enums.DropOffType;
import com.krabelard.model.enums.PickupType;
import com.krabelard.model.enums.TimePoint;
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
        var expectedSize = 7;
        var expectedStopTime = StopTime.builder()
                .tripId("22NB9AM")
                .stopId("A")
                .stopSequence(1)
                .departureTime(LocalTime.of(9, 0, 0))
                .arrivalTime(LocalTime.of(9, 0, 0))
                .continuousPickup(PickupType.NoPickup)
                .continuousDropOff(DropOffType.NoDropOff)
                .timePoint(TimePoint.Exact)
                .build();
        assertEquals(expectedSize, stopTimes.size());
        assertEquals(expectedStopTime, stopTimes.get(0));
    }
}
