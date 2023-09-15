package com.krabelard.parsers.optional;

import com.krabelard.model.optional.FeedInfo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FeedInfoParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var feedInfo = FeedInfoParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 1;
        var expectedFeedInfo = FeedInfo.builder()
                .feedPublisherName("Transport for Cairo")
                .feedPublisherUrl("http://transportforcairo.com/")
                .feedLanguage("en")
                .feedStartDate(LocalDate.of(2016, Month.JANUARY, 1))
                .feedEndDate(LocalDate.of(2016, Month.DECEMBER, 1))
                .feedVersion("0.5")
                .build();
    }

    @Test
    void shouldThrowWhenFileNotPresent() {
        var parser = FeedInfoParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}
