package com.krabelard.parsers.optional;

import com.krabelard.model.enums.TableName;
import com.krabelard.model.optional.Translation;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TranslationParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var translations = TranslationParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 1;
        var expectedTranslation = Translation.builder()
                .tableName(TableName.Stops)
                .fieldName("stop_name")
                .recordId("S8815040")
                .language("nl")
                .translation("Brussel-West")
                .build();
        assertEquals(expectedSize, translations.size());
        assertEquals(expectedTranslation, translations.get(0));
    }

    @Test
    void shouldThrowWhenFileNotPresent() {
        var parser = TranslationParser.of(TestConstants.EMPTY_DIR);
        assertThrows(NoSuchFileException.class, parser::parse);
    }
}