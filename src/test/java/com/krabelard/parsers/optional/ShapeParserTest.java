package com.krabelard.parsers.optional;

import com.krabelard.model.optional.Shape;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShapeParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var shapes = ShapeParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 13;
        var expectedShape = Shape.builder()
                .id("3030026")
                .ptLatitude(51.086506)
                .ptLongitude(-114.132259)
                .ptSequence(10001)
                .distTraveled(0.0)
                .build();
        assertEquals(expectedSize, shapes.size());
        assertEquals(expectedShape, shapes.get(0));
    }

    @Test
    void shouldThrowWhenFileIsNotPresent() {
        var parser = ShapeParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}