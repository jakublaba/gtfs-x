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
import java.util.Collection;

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
    public Collection<FareAttribute> parse() throws GtfsParsingException {
        try {
            var headers = CsvUtil.headersAsStrings(Headers.values());
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        log.info(r.toString());
                        var values = CsvUtil.extractValues(r, headers);
                        return FareAttribute.builder()
                                .id(values.get(Headers.FareId.value))
                                .price(Double.parseDouble(values.get(Headers.Price.value)))
                                .currency(values.get(Headers.CurrencyType.value))
                                .paymentMethod(CsvUtil.parseEnum(
                                        PaymentMethod.class,
                                        Integer.parseInt(values.get(Headers.PaymentMethod.value))
                                ))
                                .transfers(CsvUtil.parseEnum(
                                        TransfersAllowed.class,
                                        Integer.parseInt(values.get(Headers.Transfers.value))
                                ))
                                .agencyId(values.get(Headers.AgencyId.value))
                                .transferDuration(CsvUtil.parseNullableInt(values.get(Headers.TransferDuration.value)))
                                .build();
                    })
                    .toList();
        } catch (IOException e) {
            throw new GtfsParsingException(csv, e);
        }
    }

    @RequiredArgsConstructor
    @Getter
    private enum Headers implements CsvHeaders {
        FareId("fare_id"),
        Price("price"),
        CurrencyType("currency_type"),
        PaymentMethod("payment_method"),
        Transfers("transfers"),
        AgencyId("agency_id"),
        TransferDuration("transfer_duration");

        private final String value;
    }

    public static void main(String[] args) throws GtfsParsingException {
        FareAttributeParser.of("C:\\Users\\Kuba\\Desktop\\sample-feed")
                .parse()
                .forEach(System.out::println);
    }
}
