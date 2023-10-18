package com.krabelard.parsers.optional.gtfs_fares_v2;

import com.krabelard.model.optional.gtfs_fares_v2.FareProduct;
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
public class FareProductParser implements GtfsCsvParser<FareProduct> {
    private static final String GTFS_FILE_NAME = "fare_products.txt";
    private final String csv;

    private FareProductParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static FareProductParser of(String dir) {
        return new FareProductParser(dir);
    }

    @Override
    public Collection<FareProduct> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return FareProduct.builder()
                                .id(values.get(Headers.FARE_PRODUCT_ID.value))
                                .name(values.get(Headers.FARE_PRODUCT_NAME.value))
                                .fareMediaId(values.get(Headers.FARE_MEDIA_ID.value))
                                .amount(Double.parseDouble(values.get(Headers.AMOUNT.value)))
                                .currency(values.get(Headers.CURRENCY.value))
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
        FARE_PRODUCT_ID("fare_product_id"),
        FARE_PRODUCT_NAME("fare_product_name"),
        FARE_MEDIA_ID("fare_media_id"),
        AMOUNT("amount"),
        CURRENCY("currency");

        private final String value;
    }
}
