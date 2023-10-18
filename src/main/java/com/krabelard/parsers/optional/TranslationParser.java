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
import java.util.List;

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
    public List<Translation> parse() throws GtfsParsingException, NoSuchFileException {
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
                                        values.get(Headers.TABLE_NAME.value)
                                ))
                                .fieldName(values.get(Headers.FIELD_NAME.value))
                                .language(values.get(Headers.LANGUAGE.value))
                                .translation(values.get(Headers.TRANSLATION.value))
                                .recordId(CsvUtil.parseNullableString(values.get(Headers.RECORD_ID.value)))
                                .recordSubId(CsvUtil.parseNullableString(values.get(Headers.RECORD_SUB_ID.value)))
                                .fieldValue(CsvUtil.parseNullableString(values.get(Headers.FIELD_VALUE.value)))
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
        TABLE_NAME("table_name"),
        FIELD_NAME("field_name"),
        LANGUAGE("language"),
        TRANSLATION("translation"),
        RECORD_ID("record_id"),
        RECORD_SUB_ID("record_sub_id"),
        FIELD_VALUE("field_value");

        private final String value;
    }
}
