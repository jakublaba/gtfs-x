package com.krabelard.parsers.optional;

import com.krabelard.model.optional.FeedInfo;
import com.krabelard.parsers.CsvHeaders;
import com.krabelard.parsers.GtfsCsvParser;
import com.krabelard.parsers.GtfsParsingException;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class FeedInfoParser implements GtfsCsvParser<FeedInfo> {
    private static final String GTFS_FILE_NAME = "feed_info.txt";
    private final String csv;

    private FeedInfoParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static FeedInfoParser of(String dir) {
        return new FeedInfoParser(dir);
    }

    @Override
    public List<FeedInfo> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            var format = DateTimeFormatter.ofPattern("yyyyMMdd");
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return FeedInfo.builder()
                                .feedPublisherName(values.get(Headers.FEED_PUBLISHER_NAME.value))
                                .feedPublisherUrl(values.get(Headers.FEED_PUBLISHER_URL.value))
                                .feedLanguage(values.get(Headers.FEED_LANGUAGE.value))
                                .defaultLanguage(values.get(Headers.DEFAULT_LANGUAGE.value))
                                .feedStartDate(LocalDate.parse(values.get(Headers.FEED_START_DATE.value), format))
                                .feedEndDate(LocalDate.parse(values.get(Headers.FEED_END_DATE.value), format))
                                .feedVersion(values.get(Headers.FEED_VERSION.value))
                                .feedContactEmail(values.get(Headers.FEED_CONTACT_EMAIL.value))
                                .feedContactUrl(values.get(Headers.FEED_CONTACT_URL.value))
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
        FEED_PUBLISHER_NAME("feed_publisher_name"),
        FEED_PUBLISHER_URL("feed_publisher_url"),
        FEED_LANGUAGE("feed_lang"),
        DEFAULT_LANGUAGE("default_lang"),
        FEED_START_DATE("feed_start_date"),
        FEED_END_DATE("feed_end_date"),
        FEED_VERSION("feed_version"),
        FEED_CONTACT_EMAIL("feed_contact_email"),
        FEED_CONTACT_URL("feed_contact_url");

        private final String value;
    }
}
