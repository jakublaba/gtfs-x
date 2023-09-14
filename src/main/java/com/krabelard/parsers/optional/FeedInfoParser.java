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
                                .feedPublisherName(values.get(Headers.FeedPublisherName.value))
                                .feedPublisherUrl(values.get(Headers.FeedPublisherUrl.value))
                                .feedLanguage(values.get(Headers.FeedLanguage.value))
                                .defaultLanguage(values.get(Headers.DefaultLanguage.value))
                                .feedStartDate(LocalDate.parse(values.get(Headers.FeedStartDate.value), format))
                                .feedEndDate(LocalDate.parse(values.get(Headers.FeedEndDate.value), format))
                                .feedVersion(values.get(Headers.FeedVersion.value))
                                .feedContactEmail(values.get(Headers.FeedContactEmail.value))
                                .feedContactUrl(values.get(Headers.FeedContactUrl.value))
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
        FeedPublisherName("feed_publisher_name"),
        FeedPublisherUrl("feed_publisher_url"),
        FeedLanguage("feed_lang"),
        DefaultLanguage("default_lang"),
        FeedStartDate("feed_start_date"),
        FeedEndDate("feed_end_date"),
        FeedVersion("feed_version"),
        FeedContactEmail("feed_contact_email"),
        FeedContactUrl("feed_contact_url");

        private final String value;
    }
}
