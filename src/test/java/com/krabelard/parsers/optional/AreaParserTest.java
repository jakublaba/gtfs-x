package com.krabelard.parsers.optional;

import com.krabelard.model.optional.Area;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AreaParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var areas = AreaParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 3;
        var expectedArea = Area.builder()
                .id("ASHB")
                .build();
        assertEquals(expectedSize, areas.size());
        assertEquals(expectedArea, areas.get(0));
    }

    @Test
    void shouldThrowWhenFileNotPresent() {
        var parser = AreaParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}
