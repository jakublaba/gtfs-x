package com.krabelard.parsers.optional.gtfs_fares_v1;

import com.krabelard.model.enums.PaymentMethod;
import com.krabelard.model.enums.TransfersAllowed;
import com.krabelard.model.optional.gtfs_fares_v1.FareAttribute;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FareAttributeParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var fareAttributes = FareAttributeParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 1;
        var expectedFareAttribute = FareAttribute.builder()
                .fareId("presto_fare")
                .price(3.2)
                .currency("CAD")
                .paymentMethod(PaymentMethod.BeforeBoarding)
                .transfers(TransfersAllowed.Unlimited)
                .transferDuration(7200)
                .build();
        assertEquals(expectedSize, fareAttributes.size());
        assertEquals(expectedFareAttribute, fareAttributes.get(0));
    }

    @Test
    void shouldThrowWhenFileNotPresent() {
        var parser = FareAttributeParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}