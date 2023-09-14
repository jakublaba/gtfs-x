package com.krabelard.parsers.optional;

import com.krabelard.model.enums.TableName;
import com.krabelard.model.optional.Translation;
import com.krabelard.parsers.CsvHeaders;
import com.krabelard.parsers.GtfsCsvParser;
import com.krabelard.parsers.GtfsParsingException;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Collection;

@Slf4j
public class TranslationParser implements GtfsCsvParser<Translation> {
    private static final String GTFS_FILE_NAME = "translations.txt";
    private final String csv;

    private TranslationParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static TranslationParser of(String dir) {
        return new TranslationParser(dir);
    }

    @Override
    public Collection<Translation> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Translation.builder()
                                .tableName(CsvUtil.parseEnum(
                                        TableName.class,
                                        values.get(Headers.TableName.value)
                                ))
                                .fieldName(values.get(Headers.FieldName.value))
                                .language(values.get(Headers.Language.value))
                                .translation(values.get(Headers.Translation.value))
                                .recordId(values.get(Headers.RecordId.value))
                                .recordSubId(values.get(Headers.RecordSubId.value))
                                .fieldValue(values.get(Headers.FieldValue.value))
                                .build();
                    })
                    .toList();
        } catch (NoSuchFileException e) {
            throw e;
        } catch (IOException e) {
            throw new GtfsParsingException(csv, e);
        }
    }

    @RequiredArgsConstructor
    @Getter
    private enum Headers implements CsvHeaders {
        TableName("table_name"),
        FieldName("field_name"),
        Language("language"),
        Translation("translation"),
        RecordId("record_id"),
        RecordSubId("record_sub_id"),
        FieldValue("field_value");

        private final String value;
    }
}
