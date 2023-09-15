package com.krabelard.parsers.optional;

import com.krabelard.model.optional.Frequency;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FrequencyParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var frequencies = FrequencyParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 2;
        var expectedFrequency = Frequency.builder()
                .tripId("22M-GLOBAUX-00-S_1_2")
                .startTime(LocalTime.of(16, 1, 25))
                .endTime(LocalTime.of(16, 19, 25))
                .headwaySecs(180)
                .build();
        assertEquals(expectedSize, frequencies.size());
        assertEquals(expectedFrequency, frequencies.get(0));
    }

    @Test
    void shouldThrowWhenFileIsNotPresent() {
        var parser = FrequencyParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}