package com.krabelard.parsers.optional.gtfs_fares_v1;

import com.krabelard.model.enums.PaymentMethod;
import com.krabelard.model.enums.TransfersAllowed;
import com.krabelard.model.optional.gtfs_fares_v1.FareAttribute;
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
public class FareAttributeParser implements GtfsCsvParser<FareAttribute> {
    private static final String GTFS_FILE_NAME = "fare_attributes.txt";
    private final String csv;

    private FareAttributeParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static FareAttributeParser of(String dir) {
        return new FareAttributeParser(dir);
    }

    @Override
    public List<FareAttribute> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return FareAttribute.builder()
                                .fareId(values.get(Headers.FARE_ID.value))
                                .price(Double.parseDouble(values.get(Headers.PRICE.value)))
                                .currency(values.get(Headers.CURRENCY_TYPE.value))
                                .paymentMethod(CsvUtil.parseEnum(
                                        PaymentMethod.class,
                                        Integer.parseInt(values.get(Headers.PAYMENT_METHOD.value))
                                ))
                                .transfers(CsvUtil.parseEnum(
                                        TransfersAllowed.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.TRANSFERS.value))
                                ))
                                .agencyId(values.get(Headers.AGENCY_ID.value))
                                .transferDuration(CsvUtil.parseNullableInt(values.get(Headers.TRANSFER_DURATION.value)))
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
        FARE_ID("fare_id"),
        PRICE("price"),
        CURRENCY_TYPE("currency_type"),
        PAYMENT_METHOD("payment_method"),
        TRANSFERS("transfers"),
        AGENCY_ID("agency_id"),
        TRANSFER_DURATION("transfer_duration");

        private final String value;
    }
}
