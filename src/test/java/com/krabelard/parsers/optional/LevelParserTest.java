package com.krabelard.parsers.optional;

import com.krabelard.model.optional.Level;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LevelParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var levels = LevelParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 4;
        var expectedLevel = Level.builder()
                .id("L0")
                .index(0)
                .name("Street")
                .build();
        assertEquals(expectedSize, levels.size());
        assertEquals(expectedLevel, levels.get(0));
    }

    @Test
    void shouldThrowWhenFileIsNotPresent() {
        var parser = LevelParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}