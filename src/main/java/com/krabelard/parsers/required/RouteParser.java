package com.krabelard.parsers.required;

import com.krabelard.model.enums.DropOffType;
import com.krabelard.model.enums.PickupType;
import com.krabelard.model.enums.RouteType;
import com.krabelard.model.required.Route;
import com.krabelard.parsers.CsvHeaders;
import com.krabelard.parsers.GtfsCsvParser;
import com.krabelard.parsers.GtfsParsingException;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class RouteParser implements GtfsCsvParser<Route> {
    private static final String GTFS_FILE_NAME = "routes.txt";
    private final String csv;

    private RouteParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static RouteParser of(String dir) {
        return new RouteParser(dir);
    }

    @Override
    public List<Route> parse() throws GtfsParsingException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Route.builder()
                                .id(values.get(Headers.ROUTE_ID.value))
                                .agencyId(CsvUtil.parseNullableString(values.get(Headers.AGENCY_ID.value)))
                                .shortName(CsvUtil.parseNullableString(values.get(Headers.ROUTE_SHORT_NAME.value)))
                                .longName(CsvUtil.parseNullableString(values.get(Headers.ROUTE_LONG_NAME.value)))
                                .description(CsvUtil.parseNullableString(values.get(Headers.ROUTE_DESCRIPTION.value)))
                                .type(CsvUtil.parseEnum(
                                        RouteType.class,
                                        Integer.parseInt(values.get(Headers.ROUTE_TYPE.value))
                                ))
                                .url(CsvUtil.parseNullableString(values.get(Headers.ROUTE_URL.value)))
                                .color(CsvUtil.parseNullableString(values.get(Headers.ROUTE_COLOR.value)))
                                .textColor(CsvUtil.parseNullableString(values.get(Headers.ROUTE_TEXT_COLOR.value)))
                                .sortOrder(CsvUtil.parseNullableInt(values.get(Headers.ROUTE_SORT_ORDER.value)))
                                .continuousPickup(CsvUtil.parseEnum(
                                        PickupType.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.CONTINUOUS_PICKUP.value))
                                ))
                                .continuousDropOff(CsvUtil.parseEnum(
                                        DropOffType.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.CONTINUOUS_DROP_OFF.value))
                                ))
                                .networkId(CsvUtil.parseNullableString(values.get(Headers.NETWORK_ID.value)))
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
        ROUTE_ID("route_id"),
        AGENCY_ID("agency_id"),
        ROUTE_SHORT_NAME("route_short_name"),
        ROUTE_LONG_NAME("route_long_name"),
        ROUTE_DESCRIPTION("route_desc"),
        ROUTE_TYPE("route_type"),
        ROUTE_URL("route_url"),
        ROUTE_COLOR("route_color"),
        ROUTE_TEXT_COLOR("route_text_color"),
        ROUTE_SORT_ORDER("route_sort_order"),
        CONTINUOUS_PICKUP("continuous_pickup"),
        CONTINUOUS_DROP_OFF("continuous_drop_off"),
        NETWORK_ID("network_id");

        private final String value;
    }
}
