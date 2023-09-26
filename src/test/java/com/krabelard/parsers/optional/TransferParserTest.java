package com.krabelard.parsers.optional;

import com.krabelard.model.enums.TransferType;
import com.krabelard.model.optional.Transfer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransferParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var transfers = TransferParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 2;
        var expectedTransfer = Transfer.builder()
                .fromStopId("Bloor")
                .toStopId("Yonge")
                .fromRouteId("line1")
                .toRouteId("line2")
                .transferType(TransferType.Recommended)
                .build();
        assertEquals(expectedSize, transfers.size());
        assertEquals(expectedTransfer, transfers.get(0));
    }

    @Test
    void shouldThrowWhenFileIsNotPresent() {
        var parser = TransferParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}