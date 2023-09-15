package com.krabelard.parsers.optional;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FeedInfoParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var feedInfo = FeedInfoParser.of(TestConstants.FEED_DIR).parse();
        // TODO
    }

    @Test
    void shouldThrowWhenFileNotPresent() {
        var parser = FeedInfoParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}
