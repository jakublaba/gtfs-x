package com.krabelard.parsers.optional;

import com.krabelard.model.optional.StopArea;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StopAreaParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var stopAreas = StopAreaParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 3;
        var expectedStopArea = StopArea.builder()
                .areaId("ASHB")
                .stopId("ASHB")
                .build();
        assertEquals(expectedSize, stopAreas.size());
        assertEquals(expectedStopArea, stopAreas.get(0));
    }

    @Test
    void shouldThrowWhenFileIsNotPresent() {
        var parser = StopAreaParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}