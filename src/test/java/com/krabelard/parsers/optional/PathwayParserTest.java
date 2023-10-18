package com.krabelard.parsers.optional;

import com.krabelard.model.enums.PathwayMode;
import com.krabelard.model.optional.Pathway;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PathwayParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var pathways = PathwayParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 2;
        var expectedPathway = Pathway.builder()
                .id("stairsA")
                .fromStopId("90")
                .toStopId("95")
                .mode(PathwayMode.STAIRS)
                .isBidirectional(true)
                .build();
        assertEquals(expectedSize, pathways.size());
        assertEquals(expectedPathway, pathways.get(0));
    }

    @Test
    void shouldThrowWhenFileIsNotPresent() {
        var parser = PathwayParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}